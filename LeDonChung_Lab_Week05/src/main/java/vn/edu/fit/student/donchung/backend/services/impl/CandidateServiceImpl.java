package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.CandidateMapper;
import vn.edu.fit.student.donchung.backend.converters.CandidateSkillMapper;
import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.entities.*;
import vn.edu.fit.student.donchung.backend.repositories.CandidateRepository;
import vn.edu.fit.student.donchung.backend.repositories.CandidateSkillRepository;
import vn.edu.fit.student.donchung.backend.repositories.SkillRepository;
import vn.edu.fit.student.donchung.backend.services.CandidateService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateSkillMapper candidateSkillMapper;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

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

    @Override
    public CandidateDto getCandidateId(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        if (candidate != null) {
            return candidateMapper.toDto(candidate);
        }
        return null;
    }

    @Override
    public CandidateDto updateCandidate(CandidateDto candidateDto) {

        Candidate candidate = null;
        if (candidateDto.getId() != null) {
            candidate = candidateRepository.findById(candidateDto.getId()).orElse(null);
            if (candidate == null) {
                return null;
            } else {
                candidate = candidateMapper.partialUpdate(candidateDto, candidate);
            }
        } else {
            candidate = candidateMapper.toEntity(candidateDto);

        }

        Candidate finalCandidate = candidate;
        candidate.getCandidateSkills().forEach((candidateSkill) -> {
            Skill skill = skillRepository.findById(candidateSkill.getSkill().getId()).orElse(null);


            assert skill != null;


            candidateSkill.setSkillLevel(candidateSkill.getSkillLevel());
            candidateSkill.setCandidate(finalCandidate);
            candidateSkill.setMoreInfos(candidateSkill.getMoreInfos());
            candidateSkill.setSkill(skill);
            candidateSkill.setId(CandidateSkillId.builder()
                    .canId(finalCandidate.getId())
                    .skillId(skill.getId())
                    .build());
        });


        candidate = candidateRepository.save(candidate);

        return candidateMapper.toDto(candidate);

    }
}
