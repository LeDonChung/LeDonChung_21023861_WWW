package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    @Modifying
    @Query("delete from CandidateSkill cs where cs.candidate.id = ?1 and cs.skill.id = ?2")
    void deleteByCandidateIdAndSkillId(Long candidateId, Long skillId);
}
