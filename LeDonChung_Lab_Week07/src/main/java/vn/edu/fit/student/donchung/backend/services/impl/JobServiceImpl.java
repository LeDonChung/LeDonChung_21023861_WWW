package vn.edu.fit.student.donchung.backend.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.JobMapper;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;
import vn.edu.fit.student.donchung.backend.entities.Candidate;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;
import vn.edu.fit.student.donchung.backend.entities.Job;
import vn.edu.fit.student.donchung.backend.entities.JobSkill;
import vn.edu.fit.student.donchung.backend.repositories.CandidateRepository;
import vn.edu.fit.student.donchung.backend.repositories.JobRepository;
import vn.edu.fit.student.donchung.backend.repositories.JobSkillRepository;
import vn.edu.fit.student.donchung.backend.services.JobService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public PageDto<JobDto> getJobs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Job> pageJob = jobRepository.findAll(pageRequest);

        // return linked hash list
        List<JobDto> jobDtos = pageJob.stream().map(jobMapper::toDto).toList();

        return PageDto.<JobDto>builder()
                .page(pageJob.getNumber())
                .size(pageJob.getSize())
                .total(pageJob.getNumberOfElements())
                .totalPages(pageJob.getTotalPages())
                .values(jobDtos)
                .build();
    }

    @Override
    public PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Job> pageJob = jobRepository.findJobsForCandidateWithLevel(candidateId, 20, pageRequest);
        return PageDto.<JobDto>builder()
                .page(pageJob.getNumber())
                .size(pageJob.getSize())
                .total(pageJob.getNumberOfElements())
                .totalPages(pageJob.getTotalPages())
                .values(pageJob.stream().map(jobMapper::toDto).toList())
                .build();
    }

    @Override
    public JobDto getJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        if (job == null) {
            return null;
        }
        return jobMapper.toDto(job);
    }
}
