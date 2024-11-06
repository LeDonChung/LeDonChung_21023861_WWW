package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;

import java.util.List;

public interface CandidateService {
    public List<CandidateSkillDto> getSkillsByCandidateId(Long candidateId);
}
