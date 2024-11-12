package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;

import java.util.List;

public interface CandidateService {
    /**
     * Get all candidate skill by candidate id
     * @param candidateId is id of candidate
     * @return list of candidate skill
     */
    public List<CandidateSkillDto> getSkillsByCandidateId(Long candidateId);
}
