package vn.edu.fit.student.donchung.frontend.models;

import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;

public interface JobModel {
    PageDto<JobDto> getJobs(int page, int size);
    PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId);
}
