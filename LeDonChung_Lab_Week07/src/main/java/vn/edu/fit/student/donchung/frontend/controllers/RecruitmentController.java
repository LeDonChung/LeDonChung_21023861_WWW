package vn.edu.fit.student.donchung.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;
import vn.edu.fit.student.donchung.frontend.config.UserDetails;
import vn.edu.fit.student.donchung.frontend.models.JobModel;

import java.security.Principal;

@Controller
@RequestMapping("/recruitments")
@PreAuthorize("hasAnyAuthority('COMPANY')")
public class RecruitmentController {

    @Autowired
    private JobModel jobModel;
    @GetMapping({"", "/dashboard", "/"})
    public String recruitment(Model model, Principal principal) {
        if(principal == null) {
            return "redirect:/login";
        }
        Authentication authentication = (Authentication) principal;

        // Role COMPANY -> show ứng viên phù hợp với các job đang tuyển

        // Lấy toàn bộ job của company



        Long candidateId = ((UserDetails) authentication.getPrincipal()).getUser().getId();

        // Số việc làm
        int countJob = jobModel.countJobByCompanyId(candidateId);

        model.addAttribute("countJob", countJob);
        return "recruitments/dashboard";
    }

    @GetMapping("/active")
    public String active(Model model, Principal principal,
                         @RequestParam(defaultValue = "0", required = false) Integer page,
                         @RequestParam(defaultValue = "12", required = false) Integer size) {
        if (principal == null) {
            return "redirect:/login";
        }
        Authentication authentication = (Authentication) principal;

        Long companyId = ((UserDetails) authentication.getPrincipal()).getUser().getId();

        if(page == null) {
            page = 0;
        }

        if(size == null) {
            size = 12;
        }

        // Get jobs by company
        PageDto<JobDto> pageJobs = jobModel.getJobsByCompanyId(page, size, companyId);
        model.addAttribute("jobs", pageJobs);
        return "recruitments/jobs-active";
    }

    @GetMapping("/job/{jobId}")
    public String job(Model model, Principal principal,
                      @PathVariable Long jobId,
                      @RequestParam(defaultValue = "0", required = false) Integer page,
                      @RequestParam(defaultValue = "6", required = false) Integer size,
                      @RequestParam(defaultValue = "80", required = false) Integer per){
        if (principal == null) {
            return "redirect:/login";
        }

        if(page == null) {
            page = 0;
        }

        if(size == null) {
            size = 12;
        }

        if(per == null) {
            per = 80;
        }

        // Get jobs by company
        JobDto job = jobModel.getJob(jobId);

        // Get page candidate
        PageDto<CandidateDto> pageCandidate = jobModel.getCandidatesForJobWithLevel(jobId, per, page, size);

        model.addAttribute("job", job);
        model.addAttribute("candidates", pageCandidate);
        model.addAttribute("per", per);
        return "recruitments/job-detail";
    }
    @GetMapping("/job")
    public String job(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        return "recruitments/job-detail";
    }

}
