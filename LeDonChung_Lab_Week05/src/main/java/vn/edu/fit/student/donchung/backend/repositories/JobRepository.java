package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.fit.student.donchung.backend.entities.Candidate;
import vn.edu.fit.student.donchung.backend.entities.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    /**
     * Find jobs for candidate with matching percentage
     * @param candidateId is the id of the candidate
     * @param per  is the percentage of matching skills
     * @return list of jobs
     */
    @Query("SELECT j FROM Job j " +
            "JOIN j.jobSkills js " +  // Liên kết với các kỹ năng yêu cầu của công việc
            "JOIN CandidateSkill cs ON cs.skill.id = js.skill.id " +  // Liên kết với các kỹ năng của ứng viên
            "WHERE cs.candidate.id = :candidateId " +  // Lọc theo ứng viên
            "AND cs.skillLevel >= js.skillLevel " +  // Kiểm tra mức độ kỹ năng ứng viên
            "GROUP BY j.id " +  // Nhóm theo công việc
            "HAVING COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT jss.skill.id) FROM JobSkill jss WHERE jss.job.id = j.id) >= :per " +  // So sánh tỷ lệ kỹ năng
            "ORDER BY COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT jss.skill.id) FROM JobSkill jss WHERE jss.job.id = j.id) DESC")  // Sắp xếp theo tỷ lệ
    Page<Job> findJobsForCandidateWithLevel(@Param("candidateId") Long candidateId, @Param("per") int per,
                                            Pageable pageable);


    @Query("SELECT c FROM Candidate c " +
            "JOIN c.candidateSkills cs  " +  // Liên kết kỹ năng của ứng viên với kỹ năng công việc
            "JOIN JobSkill js ON cs.skill.id = js.skill.id " +
            "WHERE js.job.id = :jobId " +  // Liên kết với công việc cụ thể
            "AND cs.skillLevel >= js.skillLevel " +  // Kiểm tra mức độ kỹ năng ứng viên đáp ứng yêu cầu
            "GROUP BY c.id " +  // Nhóm theo ứng viên
            "HAVING COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT js2.skill.id) FROM JobSkill js2 WHERE js2.job.id = :jobId) >= :per " +  // So sánh tỷ lệ kỹ năng
            "ORDER BY COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT js2.skill.id) FROM JobSkill js2 WHERE js2.job.id = :jobId) DESC")  // Sắp xếp theo tỷ lệ
    Page<Candidate> findCandidatesForJobWithLevel(@Param("jobId") Long jobId, @Param("per") int per,
                                                  Pageable pageable);

    @Query("SELECT COUNT(j) FROM Job j WHERE j.company.id = :companyId")
    int countByCompanyId(Long companyId);

    @Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
    Page<Job> findByCompanyId(Long companyId, Pageable pageable);
}
