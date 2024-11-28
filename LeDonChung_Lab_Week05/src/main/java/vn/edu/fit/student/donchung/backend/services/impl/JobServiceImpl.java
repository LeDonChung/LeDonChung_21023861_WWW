package vn.edu.fit.student.donchung.backend.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.CandidateMapper;
import vn.edu.fit.student.donchung.backend.converters.JobMapper;
import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;
import vn.edu.fit.student.donchung.backend.entities.*;
import vn.edu.fit.student.donchung.backend.repositories.*;
import vn.edu.fit.student.donchung.backend.services.JobService;
import vn.edu.fit.student.donchung.backend.specifications.JobSpecification;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobServiceImpl implements JobService {
    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public PageDto<JobDto> getJobs(int page, int size) {
        // Create page request
        PageRequest pageRequest = PageRequest.of(page, size);

        // Get all jobs
        Page<Job> pageJob = jobRepository.findAll(pageRequest);

        // Convert jobs to jobDtos
        List<JobDto> jobDtos = pageJob.stream().map(jobMapper::toDto).toList();

        return PageDto.<JobDto>builder()
                .page(page)
                .size(size)
                .total(pageJob.getNumberOfElements())
                .totalPages(pageJob.getTotalPages())
                .values(jobDtos)
                .build();
    }

    @Override
    public PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId) {
        // Create page request
        PageRequest pageRequest = PageRequest.of(page, size);


        Page<Job> pageJob = jobRepository.findJobsForCandidateWithLevel(candidateId, 20, pageRequest);
        return PageDto.<JobDto>builder()
                .page(page)
                .size(size)
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

    @Override
    public Integer countJobByCompanyId(Long companyId) {
        // Get company by id
        Company company = companyRepository.findById(companyId).orElse(null);

        // Initialize count = 0
        Integer count = 0;


        // If company is not null, count the number of jobs by company
        if (company != null) {
            count = jobRepository.countByCompanyId(company.getId());
        }

        return count;
    }

    @Override
    public PageDto<JobDto> getJobsByCompanyId(int page, int size, Long companyId) {
        // Create page request
        PageRequest pageRequest = PageRequest.of(page, size);

        // Get all jobs by company id
        Page<Job> pageJob = jobRepository.findByCompanyId(companyId, pageRequest);

        return PageDto.<JobDto>builder()
                .page(page)
                .size(size)
                .total(pageJob.getNumberOfElements())
                .totalPages(pageJob.getTotalPages())
                .values(pageJob.stream().map(jobMapper::toDto).toList())
                .build();
    }

    @Override
    public JobDto saveJob(JobDto jobDto) {
        Company company = companyRepository.findById(jobDto.getCompany().getId()).orElse(null);
        if (company == null) {
            return null;
        }

        Job job = null;
        if (jobDto.getId() != null) {
            job = jobRepository.findById(jobDto.getId()).orElse(null);
            if (job == null) {
                return null;
            } else {
                job = jobMapper.partialUpdate(jobDto, job);
            }
        } else {
            job = jobMapper.toEntity(jobDto);

        }


        job.setCompany(company);

        Job finalJob = job;
        job.getJobSkills().forEach((jobSkill) -> {
            Skill skill = skillRepository.findById(jobSkill.getSkill().getId()).orElse(null);


            assert skill != null;


            jobSkill.setSkillLevel(jobSkill.getSkillLevel());
            jobSkill.setJob(finalJob);
            jobSkill.setMoreInfos(jobSkill.getMoreInfos());
            jobSkill.setSkill(skill);
            jobSkill.setId(JobSkillId.builder()
                    .jobId(finalJob.getId())
                    .skillId(skill.getId())
                    .build());
        });


        job = jobRepository.save(job);

        return jobMapper.toDto(job);
    }

    @Override
    public PageDto<CandidateDto> findCandidatesForJobWithLevel(Long jobId, int per, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Candidate> pageCandidates = jobRepository.findCandidatesForJobWithLevel(jobId, per, pageRequest);

        return PageDto.<CandidateDto>builder()
                .page(page)
                .size(size)
                .total(pageCandidates.getNumberOfElements())
                .totalPages(pageCandidates.getTotalPages())
                .values(pageCandidates.stream().map(candidateMapper::toDto).toList())
                .build();
    }

    @Override
    public PageDto<JobDto> searchJobs(String filter, String address, int page, int size) {
        Specification<Job> spec = Specification.where(JobSpecification.hasFilter(filter))
                .and(JobSpecification.hasAddress(address));

        Page<Job> pageJob = jobRepository.findAll(spec, PageRequest.of(page, size));

        return PageDto.<JobDto>builder()
                .page(page)
                .size(size)
                .total(pageJob.getNumberOfElements())
                .totalPages(pageJob.getTotalPages())
                .values(pageJob.stream().map(jobMapper::toDto).toList())
                .build();
    }
}
