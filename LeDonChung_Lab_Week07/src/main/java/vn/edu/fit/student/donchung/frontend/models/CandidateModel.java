package vn.edu.fit.student.donchung.frontend.models;

import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.dtos.SkillDto;

import java.util.List;

public interface CandidateModel {
    public List<CandidateSkillDto> getSkillsByCandidateId(Long candidateId);

}
