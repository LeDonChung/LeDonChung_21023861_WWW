package vn.edu.iuh.fit.donchung.repositories;

import vn.edu.iuh.fit.donchung.entity.Job;
import vn.edu.iuh.fit.donchung.entity.Skill;

import java.util.List;

public interface SkillRepository {
    public Skill save(Skill skill);
    public Skill findById(int id);
    public boolean delete(int id);
    public List<Skill> findAll();
    List<Skill> findByJobId(Job job);
}
