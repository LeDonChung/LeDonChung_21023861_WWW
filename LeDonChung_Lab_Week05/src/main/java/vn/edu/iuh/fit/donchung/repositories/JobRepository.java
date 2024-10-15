package vn.edu.iuh.fit.donchung.repositories;

import vn.edu.iuh.fit.donchung.entity.Job;

import java.util.List;

public interface JobRepository {
    public Job save(Job job);
    public Job findById(int id);
    public boolean delete(int id);
    public List<Job> findAll();
}
