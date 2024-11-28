package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;
import vn.edu.fit.student.donchung.backend.entities.JobSkill;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long>{
    @Modifying
    @Query("DELETE FROM JobSkill c WHERE c.id.jobId = ?1 AND c.id.skillId = ?2")
    void removeByJobIdAndSkillId(Long candidateId, Long skillId);

}
