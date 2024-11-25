package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.fit.student.donchung.backend.entities.Candidate;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
    @Query("SELECT cs.candidate.id AS candidateId, cs.skill.id AS skillId, cs.skillLevel AS skillLevel " +
            "FROM CandidateSkill cs")
    List<Object[]> findCandidateSkills();

    @Query("SELECT js.job.id AS jobId, js.skill.id AS skillId, js.skillLevel AS skillLevel " +
            "FROM JobSkill js")
    List<Object[]> findJobSkills();

    @Query("SELECT cs.skill.id AS skillId, cs.skillLevel AS skillLevel " +
            "FROM CandidateSkill cs " +
            "WHERE cs.candidate.id = ?1")
    List<Object[]> findSkillsByCandidateId(Long candidateId);
}
