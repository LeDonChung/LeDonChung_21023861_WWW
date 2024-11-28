package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.JobSkillDto;

public interface JobSkillService {
    void removeCandidateSkill(Long jobId, Long skillId);
}
