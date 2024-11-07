package vn.edu.fit.student.donchung.frontend.models;

import org.springframework.data.domain.Page;
import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;

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
