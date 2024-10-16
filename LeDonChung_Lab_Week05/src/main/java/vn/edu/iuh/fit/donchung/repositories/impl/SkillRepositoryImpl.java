package vn.edu.iuh.fit.donchung.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.donchung.entity.Skill;
import vn.edu.iuh.fit.donchung.repositories.SkillRepository;
import vn.edu.iuh.fit.donchung.repositories.mapper.SkillMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class SkillRepositoryImpl implements SkillRepository {
    private final JdbcTemplate jdbcTemplate;

    public SkillRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Skill save(Skill skill) {
        String sql;
        if(skill.getId() == null){
            sql = "INSERT INTO skills(skill_name, description, field) VALUES(?, ?, ?)";
            KeyHolder key = new GeneratedKeyHolder();
            int id = jdbcTemplate.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, skill.getSkillName());
                ps.setString(2, skill.getDescription());
                ps.setString(3, skill.getField());
                return ps;
            }, key);

            if(id > 0){
                skill.setId(Objects.requireNonNull(key.getKey()).intValue());
                return skill;
            }
        } else {
            sql = "UPDATE skills SET skill_name = ?, description = ?, field = ? WHERE id = ?";
            int result = jdbcTemplate.update(sql, skill.getSkillName(), skill.getDescription(), skill.getField(), skill.getId());
            if(result > 0){
                return skill;
            }
        }
        return null;
    }

    @Override
    public Skill findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM skills WHERE id = ?", new SkillMapper(), id);
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM skills WHERE id = ?", id) > 0;
    }

    @Override
    public List<Skill> findAll() {
        return jdbcTemplate.query("SELECT * FROM skills", new SkillMapper());
    }
}
