package vn.edu.fit.student.donchung.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fit.student.donchung.backend.dtos.*;
import vn.edu.fit.student.donchung.frontend.config.UserDetails;
import vn.edu.fit.student.donchung.frontend.models.CandidateModel;
import vn.edu.fit.student.donchung.frontend.models.JobModel;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobModel jobModel;
    @Autowired
    private CandidateModel candidateModel;
    @GetMapping("/detail/{jobId}")
    public String jobDetail(@PathVariable String jobId, Principal principal, Model model) {

        Authentication authentication = (Authentication) principal;
        JobDto job = jobModel.getJob(Long.parseLong(jobId));


        if(authentication != null) {
            if(authentication.isAuthenticated()) {
                Long candidateId = ((UserDetails) authentication.getPrincipal()).getUser().getId();

                List<JobSkillDto> skillsOfJob = job.getJobSkills();

                List<CandidateSkillDto> skills = candidateModel.getSkillsByCandidateId(candidateId);

                List<CandidateSkillDto> skillHave = skills.stream().map((value) -> {
                    for (JobSkillDto skillOfJob : skillsOfJob) {
                        if (skillOfJob.getSkill().getId().equals(value.getSkill().getId())) {
                            return value;
                        }
                    }
                    return null;
                }).filter(Objects::nonNull).toList();


                // not have in job
                List<JobSkillDto> skillNotHave = skillsOfJob.stream().map((value) -> {
                    for (CandidateSkillDto skill : skills) {
                        if (skill.getSkill().getId().equals(value.getSkill().getId())) {
                            return null;
                        }
                    }
                    return value;
                }).filter(Objects::nonNull).toList();

                int match = (int) Math.ceil((double) (skillHave.size() * 100) / skillsOfJob.size());

                model.addAttribute("match", match);
                model.addAttribute("skillHave", skillHave);
                model.addAttribute("skillNotHave", skillNotHave);
            }
        }


        model.addAttribute("job", job);
        return "job-detail";
    }
}
