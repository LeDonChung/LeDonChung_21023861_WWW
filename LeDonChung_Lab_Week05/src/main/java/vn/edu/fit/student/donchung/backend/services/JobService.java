package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;

import java.awt.print.Pageable;

public interface JobService {
    /**
     * Get all job with pagination
     * @param page is page number
     * @param size is size of page
     * @return list of job
     */
    public PageDto<JobDto> getJobs(int page, int size);

    /**
     * Get all job for candidate with pagination
     * @param page is page number
     * @param size is size of page
     * @param candidateId is id of candidate
     * @return list of job
     */
    public PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId);

    /**
     * Get job by id
     * @param jobId is id of job
     * @return job
     */
    JobDto getJob(Long jobId);

    /**
     * Count job by company id
     * @param companyId is id of company
     * @return number of job
     */
    Integer countJobByCompanyId(Long companyId);

    /**
     * Get all job by company id with pagination
     * @param page is page number
     * @param size is size of page
     * @param companyId is id of company
     * @return list of job
     */
    PageDto<JobDto> getJobsByCompanyId(int page, int size, Long companyId);

    /**
     * Save job - if job id is null then create new job else update job
     * @param jobDto is job
     * @return job saved if success null is fail
     */
    JobDto saveJob(JobDto jobDto);

    PageDto<CandidateDto> findCandidatesForJobWithLevel(Long jobId, int per, int page, int size);

    PageDto<JobDto> searchJobs(String filter, String address, int page, int size);
}
