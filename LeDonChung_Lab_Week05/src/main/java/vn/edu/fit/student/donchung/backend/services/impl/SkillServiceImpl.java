package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.SkillMapper;
import vn.edu.fit.student.donchung.backend.dtos.SkillDto;
import vn.edu.fit.student.donchung.backend.entities.Skill;
import vn.edu.fit.student.donchung.backend.repositories.SkillRepository;
import vn.edu.fit.student.donchung.backend.services.SkillService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private SkillMapper skillMapper;
    @Override
    public List<SkillDto> getAllList() {

        List<Skill> skills = skillRepository.findAll();
        return skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
    }
}
