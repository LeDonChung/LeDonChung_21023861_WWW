package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.SkillDto;
import vn.edu.fit.student.donchung.backend.entities.Skill;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {
    Skill toEntity(SkillDto skillDto);

    SkillDto toDto(Skill skill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Skill partialUpdate(SkillDto skillDto, @MappingTarget Skill skill);
}