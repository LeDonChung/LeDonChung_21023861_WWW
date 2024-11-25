package vn.edu.fit.student.donchung.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.frontend.dto.*;
import vn.edu.fit.student.donchung.frontend.models.JobModel;
import vn.edu.fit.student.donchung.frontend.utils.AppUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @Override
    public Integer countJobByCompanyId(Long companyId) {
        return restTemplate.getForObject(AppUtils.API_URL + "/jobs/count?companyId=" + companyId,
                Integer.class);
    }

    @Override
    public PageDto<JobDto> getJobsByCompanyId(int page, int size, Long companyId) {
        return restTemplate.getForObject(AppUtils.API_URL + "/jobs/company?page=" + page + "&size=" + size + "&companyId=" + companyId,
                PageDto.class);
    }

    @Override
    public JobDto createJob(JobDto jobDto) {
        System.out.println("BEGIN: " + jobDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<JobDto> request = new HttpEntity<>(jobDto, headers);

        ResponseEntity<JobDto> response = restTemplate.exchange(
                AppUtils.API_URL + "/jobs",
                HttpMethod.POST,
                request,
                JobDto.class);

        return response.getBody();
    }

    @Override
    public JobDto updateJob(JobDto jobDto) {
//        try{
//            ResponseEntity<JobDto> response = restTemplate.exchange(
//                    AppUtils.API_URL + "/jobs",
//                    org.springframework.http.HttpMethod.PUT,
//                    new HttpEntity<>(jobDto),
//                    JobDto.class);
//
//            return response.getBody();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        return restTemplate.postForObject(AppUtils.API_URL + "/jobs", jobDto,
                JobDto.class);
    }

    @Override
    public PageDto<CandidateDto> getCandidatesForJobWithLevel(Long jobId, int per, int page, int size) {
        ResponseEntity<PageDto<CandidateDto>> response = restTemplate.exchange(
                AppUtils.API_URL + "/jobs/" + jobId + "/candidates?per=" + per + "&page=" + page + "&size=" + size,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDto<CandidateDto>>() {}
        );

        PageDto<CandidateDto> pageDto = response.getBody();

        if (pageDto != null) {
            Collection<CandidateDto> candidateDtos = pageDto.getValues();
            JobDto job = getJob(jobId);
            List<JobSkillDto> jobSkills = job.getJobSkills();
            int totalJobSkills = jobSkills.size();

            candidateDtos = candidateDtos.stream().map(candidate -> {
                List<CandidateSkillDto> matchingSkills = candidate.getCandidateSkills().stream()
                        .filter(candidateSkill -> jobSkills.stream()
                                .anyMatch(jobSkill -> jobSkill.getSkill().getId().equals(candidateSkill.getSkill().getId()) &&
                                        candidateSkill.getSkillLevel().compareTo(jobSkill.getSkillLevel()) >= 0)
                        ).toList();

                candidate.setMatch((int) Math.ceil((double) matchingSkills.size() * 100 / totalJobSkills));
                return candidate;
            }).toList();

            pageDto.setValues(candidateDtos);
        }

        return pageDto;
    }
}
