package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.JobSkillIdDto;
import vn.edu.fit.student.donchung.backend.entities.JobSkillId;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JobSkillIdMapper {
    JobSkillId toEntity(JobSkillIdDto jobSkillIdDto);

    JobSkillIdDto toDto(JobSkillId jobSkillId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JobSkillId partialUpdate(JobSkillIdDto jobSkillIdDto, @MappingTarget JobSkillId jobSkillId);
}