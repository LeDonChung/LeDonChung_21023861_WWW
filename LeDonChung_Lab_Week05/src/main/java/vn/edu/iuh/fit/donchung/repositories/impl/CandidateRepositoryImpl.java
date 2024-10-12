package vn.edu.iuh.fit.donchung.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.repositories.CandidateRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {

    private final JdbcTemplate jdbcTemplate;


    public CandidateRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Candidate save(Candidate candidate) {
        // neu them moi thi tra ve id
        String sql;
        if (candidate.getId() == null) {
            // CREATE IF candidate.id IS NULL
            sql = "INSERT INTO candidates(last_name, first_name, middle_name, email, phone, address, dob) VALUES(?, ?, ?, ?, ?, ?, ?)";
        } else {
            // UPDATE IF candidate.id IS NOT NULL
            sql = "UPDATE candidates SET last_name = ?, first_name = ?, middle_name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE id = ?";
        }

        List<Object> params = new ArrayList<>(
                List.of(candidate.getLastName(), candidate.getFirstName(),
                        candidate.getMiddleName(), candidate.getEmail(),
                        candidate.getPhone(), candidate.getAddress(),
                        candidate.getDob())
        );
        if (candidate.getId() != null) {
            params.add(candidate.getId());
        }
        int result = jdbcTemplate
                .update(sql, params.toArray());

        if (result > 0) {
            if (candidate.getId() == null) {
                candidate.setId(result);
            }
            return candidate;
        }

        return null;
    }

    @Override
    public Candidate findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Candidate> findAll() {
        return null;
    }
}
