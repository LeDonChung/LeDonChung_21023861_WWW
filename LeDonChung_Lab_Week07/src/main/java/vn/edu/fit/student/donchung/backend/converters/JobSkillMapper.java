package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.JobSkillDto;
import vn.edu.fit.student.donchung.backend.entities.JobSkill;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SkillMapper.class, JobMapper.class, JobSkillIdMapper.class})
public interface JobSkillMapper {
    JobSkill toEntity(JobSkillDto jobSkillDto);

    JobSkillDto toDto(JobSkill jobSkill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JobSkill partialUpdate(JobSkillDto jobSkillDto, @MappingTarget JobSkill jobSkill);
}