package vn.edu.fit.student.donchung.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;
import vn.edu.fit.student.donchung.frontend.models.JobModel;
import vn.edu.fit.student.donchung.frontend.utils.AppUtils;

@Component
public class JobModelImpl implements JobModel {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public PageDto<JobDto> getJobs(int page, int size) {
        return restTemplate.getForObject(AppUtils.API_URL + "/jobs?page=" + page + "&size=" + size,
                PageDto.class);
    }

    @Override
    public PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId) {
        return restTemplate.getForObject(AppUtils.API_URL + "/jobs/recommend?page=" + page + "&size=" + size + "&candidateId=" + candidateId,
                PageDto.class);
    }

    @Override
    public JobDto getJob(Long jobId) {
        return restTemplate.getForObject(AppUtils.API_URL + "/jobs/" + jobId,
                JobDto.class);
    }
}
