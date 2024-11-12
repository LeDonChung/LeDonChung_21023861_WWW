package vn.edu.iuh.fit.donchung.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.entity.CandidateSkill;
import vn.edu.iuh.fit.donchung.repositories.CandidateRepository;
import vn.edu.iuh.fit.donchung.repositories.mapper.CandidateMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {

    private final JdbcTemplate jdbcTemplate;


    public CandidateRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Candidate save(Candidate candidate) {
        String sql;
        if (candidate.getId() == null) {
            sql = "INSERT INTO candidates(last_name, first_name, middle_name, email, phone, address, dob) VALUES(?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int result = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, candidate.getLastName());
                ps.setString(2, candidate.getFirstName());
                ps.setString(3, candidate.getMiddleName());
                ps.setString(4, candidate.getEmail());
                ps.setString(5, candidate.getPhone());
                ps.setString(6, candidate.getAddress());
                ps.setDate(7, candidate.getDob());
                return ps;
            }, keyHolder);

            if (result > 0) {
                candidate.setId(Objects.requireNonNull(keyHolder.getKey()).intValue()); // Lấy ID của bản ghi vừa thêm
                return candidate;
            }
        } else {
            sql = "UPDATE candidates SET last_name = ?, first_name = ?, middle_name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE id = ?";
            int result = jdbcTemplate.update(sql, candidate.getLastName(), candidate.getFirstName(),
                    candidate.getMiddleName(), candidate.getEmail(),
                    candidate.getPhone(), candidate.getAddress(),
                    candidate.getDob(), candidate.getId());
            if (result > 0) {
                return candidate;
            }
        }
        return null;
    }

    @Override
    public Candidate findById(int id) {
        String sql = "SELECT * FROM candidates WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CandidateMapper(), id);
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM candidates WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean addSkill(Candidate candidate, List<CandidateSkill> candidateSkill) {
        String sql = "INSERT INTO candidates_skills(candidate_id, skill_id, level) VALUES(?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (CandidateSkill cs : candidateSkill) {
            batchArgs.add(new Object[]{candidate.getId(), cs.getSkill().getId(), cs.getLevel()});
        }
        int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
        return result.length == candidateSkill.size();
    }

    @Override
    public boolean removeSkill(CandidateSkill candidateSkill) {
        String sql = "DELETE FROM candidates_skills WHERE candidate_id = ? AND skill_id = ?";
        return jdbcTemplate.update(sql, candidateSkill.getCandidate().getId(), candidateSkill.getSkill().getId()) > 0;
    }

    @Override
    public List<Candidate> findAll() {
        String sql = "SELECT * FROM candidates";
        return jdbcTemplate.query(sql, new CandidateMapper());
    }
}
