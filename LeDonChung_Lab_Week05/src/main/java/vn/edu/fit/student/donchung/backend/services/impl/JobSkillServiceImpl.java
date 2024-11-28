package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fit.student.donchung.backend.dtos.JobSkillDto;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;
import vn.edu.fit.student.donchung.backend.entities.Job;
import vn.edu.fit.student.donchung.backend.entities.JobSkill;
import vn.edu.fit.student.donchung.backend.entities.JobSkillId;
import vn.edu.fit.student.donchung.backend.repositories.JobRepository;
import vn.edu.fit.student.donchung.backend.repositories.JobSkillRepository;
import vn.edu.fit.student.donchung.backend.services.JobSkillService;

import java.util.Optional;

@Service
public class JobSkillServiceImpl implements JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private JobRepository jobRepository;
    @Override
    @Transactional
    public void removeCandidateSkill(Long jobId, Long skillId) {
        jobSkillRepository.removeByJobIdAndSkillId(jobId, skillId);
    }
}
