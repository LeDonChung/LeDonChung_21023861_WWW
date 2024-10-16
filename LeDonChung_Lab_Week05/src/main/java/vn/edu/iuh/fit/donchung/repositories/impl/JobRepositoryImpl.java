package vn.edu.iuh.fit.donchung.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.entity.Job;
import vn.edu.iuh.fit.donchung.entity.JobSkill;
import vn.edu.iuh.fit.donchung.repositories.JobRepository;
import vn.edu.iuh.fit.donchung.repositories.mapper.CandidateMapper;
import vn.edu.iuh.fit.donchung.repositories.mapper.JobMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class JobRepositoryImpl implements JobRepository {
    private final JdbcTemplate jdbcTemplate;

    public JobRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Job save(Job job) {
        String sql;
        if(job.getId() == null){
            sql = "INSERT INTO jobs(description) VALUES(?)";
            KeyHolder key = new GeneratedKeyHolder();
            int id = jdbcTemplate.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, job.getDescription());
                return ps;
            }, key);

            if(id > 0){
                job.setId(Objects.requireNonNull(key.getKey()).intValue());
                return job;
            }
        } else {
            sql = "UPDATE jobs SET description = ? WHERE id = ?";
            int result = jdbcTemplate.update(sql, job.getDescription(), job.getId());
            if(result > 0){
                return job;
            }
        }
        return null;
    }

    @Override
    public Job findById(int id) {
        String sql = "SELECT * FROM jobs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new JobMapper(), id);
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM jobs WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public List<Job> findAll() {
        String sql = "SELECT * FROM jobs";
        return jdbcTemplate.query(sql, new JobMapper());
    }

    @Override
    public boolean addSkill(Job job, List<JobSkill> jobSkills) {
        String sql = "INSERT INTO jobs_skills(job_id, skill_id, specific_level) VALUES(?, ?, ?)";
        List<Object[]> batch = jobSkills.stream().map(jobSkill -> new Object[]{job.getId(), jobSkill.getSkill().getId(), jobSkill.getSpecific_level()}).toList();

        int[] result = jdbcTemplate.batchUpdate(sql, batch);
        return result.length == batch.size();
    }

    @Override
    public List<Candidate> findCandidates(int jobId) {

        String sql = "SELECT c.* FROM candidates c JOIN candidates_skills cs ON c.id = cs.candidate_id JOIN jobs_skills js ON cs.skill_id = js.skill_id WHERE js.job_id = ? AND cs.level >= js.specific_level";

        return jdbcTemplate.query(sql, new CandidateMapper(), jobId);
    }
}
