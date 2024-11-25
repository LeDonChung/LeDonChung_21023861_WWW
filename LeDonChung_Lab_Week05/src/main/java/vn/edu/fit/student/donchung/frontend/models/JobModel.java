package vn.edu.fit.student.donchung.frontend.models;


import vn.edu.fit.student.donchung.frontend.dto.CandidateDto;
import vn.edu.fit.student.donchung.frontend.dto.JobDto;
import vn.edu.fit.student.donchung.frontend.dto.PageDto;

public interface JobModel {
    PageDto<JobDto> getJobs(int page, int size);
    PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId);
    JobDto getJob(Long jobId);

    Integer countJobByCompanyId(Long companyId);

    PageDto<JobDto> getJobsByCompanyId(int page, int size, Long companyId);

    JobDto createJob(JobDto jobDto);

    JobDto updateJob(JobDto jobDto);

    PageDto<CandidateDto> getCandidatesForJobWithLevel(Long jobId, int per, int page, int size);
}
