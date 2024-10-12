package vn.edu.iuh.fit.donchung.repositories.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.edu.iuh.fit.donchung.entity.Candidate;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateMapper implements RowMapper<Candidate> {

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Candidate
                .builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .address(rs.getString("address"))
                .dob(rs.getDate("dob"))
                .fullName(
                        rs.getString("last_name") + " " +
                                rs.getString("middle_name") + " " +
                                rs.getString("first_name"))
                .build();
    }
}
