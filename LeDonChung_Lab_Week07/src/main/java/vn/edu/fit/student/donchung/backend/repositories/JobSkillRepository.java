package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;
import vn.edu.fit.student.donchung.backend.entities.JobSkill;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long>{
//    List<JobSkill> findBySkillIdAndLevelLessThan(List<CandidateSkill> candidateSkills);
}
