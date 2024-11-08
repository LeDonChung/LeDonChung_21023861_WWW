package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.SkillDto;
import vn.edu.fit.student.donchung.backend.entities.Skill;

import java.util.List;

public interface SkillService {
    List<SkillDto> getAllList();
}
