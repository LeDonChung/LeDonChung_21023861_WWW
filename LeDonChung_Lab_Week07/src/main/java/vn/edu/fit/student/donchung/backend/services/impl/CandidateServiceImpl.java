package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.CandidateMapper;
import vn.edu.fit.student.donchung.backend.converters.CandidateSkillMapper;
import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.entities.Candidate;
import vn.edu.fit.student.donchung.backend.repositories.CandidateRepository;
import vn.edu.fit.student.donchung.backend.services.CandidateService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateSkillMapper candidateSkillMapper;
    @Override
    public List<CandidateSkillDto> getSkillsByCandidateId(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        if (candidate != null) {
            return candidate
                    .getCandidateSkills()
                    .stream()
                    .map(candidateSkillMapper::toDto)
                    .toList();
        }

        return new ArrayList<>();
    }
}
