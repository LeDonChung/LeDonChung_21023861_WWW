package vn.edu.fit.student.donchung.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.fit.student.donchung.backend.enums.SkillLevel;
import vn.edu.fit.student.donchung.frontend.config.UserDetails;
import vn.edu.fit.student.donchung.frontend.dto.*;
import vn.edu.fit.student.donchung.frontend.models.JobModel;
import vn.edu.fit.student.donchung.frontend.models.SkillModel;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recruitments")
@PreAuthorize("hasAnyAuthority('COMPANY')")
public class RecruitmentController {

    @Autowired
    private JobModel jobModel;
    @Autowired
    private SkillModel skillModel;

    @GetMapping({"", "/dashboard", "/"})
    public String recruitment(Model model, Principal principal) {
        if (principal == null) {
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

        if (page == null) {
            page = 0;
        }

        if (size == null) {
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
                      @RequestParam(defaultValue = "80", required = false) Integer per) {
        if (principal == null) {
            return "redirect:/login";
        }

        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 12;
        }

        if (per == null) {
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
    public String job(Model model, Principal principal,
                      @RequestParam(defaultValue = "0", required = false) Integer page,
                      @RequestParam(defaultValue = "6", required = false) Integer size) {
        if (principal == null) {
            return "redirect:/login";
        }

        Authentication authentication = (Authentication) principal;

        Long companyId = ((UserDetails) authentication.getPrincipal()).getUser().getId();

        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 12;
        }

        // Get jobs by company
        PageDto<JobDto> pageJobs = jobModel.getJobsByCompanyId(page, size, companyId);
        model.addAttribute("jobs", pageJobs);
        return "recruitments/job-manager";
    }

    @GetMapping({"/job/edit/{jobId}", "/job/edit"})
    public String editJob(Model model, Principal principal,
                          @PathVariable(required = false) Long jobId,
                          @ModelAttribute JobDto jobDto,
                          @RequestParam(required = false) String action,
                          @RequestParam(required = false) String skillId,
                          @RequestParam(required = false, defaultValue = "0") Integer num) {
        if (principal == null) {
            return "redirect:/login";
        }

        Authentication authentication = (Authentication) principal;

        Long companyId = ((UserDetails) authentication.getPrincipal()).getUser().getId();

        if (jobId != null) {
            jobDto = jobModel.getJob(jobId);
        } else {
            jobDto = new JobDto();
            jobDto.setCompany(CompanyDto.builder().id(companyId).build());
            List<JobSkillDto> jobSkills = new ArrayList<>();
            jobSkills.add(new JobSkillDto());
            jobDto.setJobSkills(
                    jobSkills
            );
        }

        if ("newSkill".equals(action)) {
            for (int i = 1; i <= num; i++) {
                JobSkillDto newJobSkill = new JobSkillDto();
                newJobSkill.setSkill(new SkillDto());
                newJobSkill.getSkill().setId((long) -i);
                jobDto.getJobSkills().add(newJobSkill);
            }
        } else if("removeSkill".equals(action)) {
            jobDto.getJobSkills().removeIf(jobSkill -> jobSkill.getSkill().getId().equals(Long.parseLong(skillId)));
        }
        List<SkillDto> skills = skillModel.getAll();
        List<SkillLevel> skillLevels = List.of(SkillLevel.values());

        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("skills", skills);
        model.addAttribute("job", jobDto);
        model.addAttribute("num", num);
        return "recruitments/job-edit";
    }

    @PostMapping("/job/edit")
    public String editJob(Model model, Principal principal, @ModelAttribute JobDto jobDto) {
        if (principal == null) {
            return "redirect:/login";
        }

        jobModel.createJob(jobDto);
        return "redirect:/recruitments/job";

    }

    @GetMapping("/job/deleteSkill/{jobId}")
    public String deleteSkill(Model model, Principal principal,
                              @PathVariable Long jobId,
                              @RequestParam Long skillId) {
        if (principal == null) {
            return "redirect:/login";
        }

        jobModel.removeById(jobId, skillId);
        return "redirect:/recruitments/job/edit/" + jobId;
    }
}
