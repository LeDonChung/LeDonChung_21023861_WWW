package vn.edu.fit.student.donchung.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.fit.student.donchung.frontend.config.UserDetails;
import vn.edu.fit.student.donchung.frontend.dto.JobDto;
import vn.edu.fit.student.donchung.frontend.dto.PageDto;
import vn.edu.fit.student.donchung.frontend.models.JobModel;
import vn.edu.fit.student.donchung.frontend.utils.RoleUtils;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private JobModel jobModel;
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model,
                        Principal principal) {
        Authentication authentication = (Authentication) principal;
        if(authentication == null) {
            PageDto<JobDto> pageJobs = jobModel.getJobs(0, 12);
            model.addAttribute("pageJobs", pageJobs);
        } else {
            if(authentication.isAuthenticated()) {
                if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(RoleUtils.ROLE_CANDIDATE))) {
                    // Role CANDIDATE -> show job phù hợp với skill đang có

                    PageDto<JobDto> pageJobs = jobModel.getJobs(0, 12);

                    Long candidateId = ((UserDetails) authentication.getPrincipal()).getUser().getId();
                    PageDto<JobDto> pageRecommend = jobModel.getJobsForCandidate(0, 12, candidateId);

                    model.addAttribute("pageRecommend", pageRecommend);
                    model.addAttribute("candidateId", candidateId);
                    model.addAttribute("pageJobs", pageJobs);

                } else if(authentication.isAuthenticated()) {
                    if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(RoleUtils.ROLE_COMPANY))) {
                        return "redirect:/recruitments";
                    }
                }
            }
        }
        return "index";
    }
}
