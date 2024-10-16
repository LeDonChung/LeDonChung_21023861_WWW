package vn.edu.iuh.fit.donchung.repositories;

import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.entity.CandidateSkill;

import java.util.List;

public interface CandidateRepository  {
    public Candidate save(Candidate candidate);
    public Candidate findById(int id);
    public boolean delete(int id);
    public boolean addSkill(Candidate candidate, List<CandidateSkill> candidateSkill);
    public boolean removeSkill(CandidateSkill candidateSkill);
    public List<Candidate> findAll();
}
