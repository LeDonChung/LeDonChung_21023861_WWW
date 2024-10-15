package vn.edu.iuh.fit.donchung.repositories.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.edu.iuh.fit.donchung.entity.Job;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMapper implements RowMapper<Job> {
    @Override
    public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Job.builder()
                .id(rs.getInt("id"))
                .description(rs.getString("description"))
                .build();
    }
}
