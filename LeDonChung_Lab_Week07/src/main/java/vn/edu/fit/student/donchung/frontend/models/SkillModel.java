package vn.edu.fit.student.donchung.frontend.models;

import vn.edu.fit.student.donchung.backend.dtos.SkillDto;
import vn.edu.fit.student.donchung.backend.entities.Skill;

import java.util.List;

public interface SkillModel {
    List<SkillDto> getAll();
}
