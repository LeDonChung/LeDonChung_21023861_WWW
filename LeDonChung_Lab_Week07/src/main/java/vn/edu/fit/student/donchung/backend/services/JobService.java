package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;

public interface JobService {
    /**
     * Get all job with pagination
     * @param page
     * @param size
     * @return
     */
    public PageDto<JobDto> getJobs(int page, int size);

    // Lấy ra job phù hợp với candidate
    public PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId);
}
