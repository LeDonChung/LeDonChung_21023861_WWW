package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.ExperienceDto;
import vn.edu.fit.student.donchung.backend.entities.Experience;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExperienceMapper {
    Experience toEntity(ExperienceDto experienceDto);

    ExperienceDto toDto(Experience experience);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Experience partialUpdate(ExperienceDto experienceDto, @MappingTarget Experience experience);
}