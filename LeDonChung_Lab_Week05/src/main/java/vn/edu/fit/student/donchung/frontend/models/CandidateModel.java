package vn.edu.fit.student.donchung.frontend.models;


import vn.edu.fit.student.donchung.frontend.dto.CandidateSkillDto;

import java.util.List;

public interface CandidateModel {
    public List<CandidateSkillDto> getSkillsByCandidateId(Long candidateId);

}
