package vn.edu.iuh.fit.donchung.repositories;

import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.entity.CandidateSkill;
import vn.edu.iuh.fit.donchung.entity.Job;
import vn.edu.iuh.fit.donchung.entity.JobSkill;

import java.util.List;

public interface JobRepository {
    public Job save(Job job);
    public Job findById(int id);
    public boolean delete(int id);
    public List<Job> findAll();
    public boolean addSkill(Job job, List<JobSkill> jobSkills);

    public List<Candidate> findCandidates(int jobId);

}
