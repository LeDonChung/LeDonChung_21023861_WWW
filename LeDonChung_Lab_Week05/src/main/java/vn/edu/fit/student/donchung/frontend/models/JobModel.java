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

    PageDto<CandidateDto> getCandidatesForJobWithLevel(Long jobId, int per, int page, int size);

    PageDto<JobDto> searchJobs(String filter, String address, int page, int size);

    void removeById(Long jobId, Long skillId);

    boolean sendMailToCandidate(Long jobId, Long candidateId);
}
