package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fit.student.donchung.backend.repositories.CandidateRepository;
import vn.edu.fit.student.donchung.backend.repositories.CandidateSkillRepository;
import vn.edu.fit.student.donchung.backend.services.CandidateSkillService;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService {
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Override
    @Transactional
    public void removeSkill(Long candidateId, Long skillId) {
        candidateSkillRepository.deleteByCandidateIdAndSkillId(candidateId, skillId);
    }
}
