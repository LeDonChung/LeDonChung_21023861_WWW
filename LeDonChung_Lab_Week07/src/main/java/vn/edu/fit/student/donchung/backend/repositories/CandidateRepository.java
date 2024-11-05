package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fit.student.donchung.backend.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
}
