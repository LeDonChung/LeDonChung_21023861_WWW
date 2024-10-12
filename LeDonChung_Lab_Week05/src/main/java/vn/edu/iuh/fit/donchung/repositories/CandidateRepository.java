package vn.edu.iuh.fit.donchung.repositories;

import vn.edu.iuh.fit.donchung.entity.Candidate;

import java.util.List;

public interface CandidateRepository  {
    public Candidate save(Candidate candidate);
    public Candidate findById(int id);
    public boolean delete(int id);
    public List<Candidate> findAll();
}
