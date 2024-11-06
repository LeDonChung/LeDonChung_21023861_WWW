package vn.edu.fit.student.donchung.backend.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;
import vn.edu.fit.student.donchung.backend.services.JobService;

@RestController
@RequestMapping("/api/jobs")
@Slf4j
public class JobRestController {

    @Autowired
    private JobService jobService;
    @GetMapping
    public ResponseEntity<PageDto<JobDto>> getPaginationJobs(int page, int size) {
        try {
            PageDto<JobDto> jobs = jobService.getJobs(page, size);
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/recommend")
    public ResponseEntity<PageDto<JobDto>> getRecommendJobs(int page, int size, Long candidateId) {
        try {
            PageDto<JobDto> jobs = jobService.getJobsForCandidate(page, size, candidateId);
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDto> getJob(@PathVariable Long jobId) {
        try {
            JobDto job = jobService.getJob(jobId);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


}
