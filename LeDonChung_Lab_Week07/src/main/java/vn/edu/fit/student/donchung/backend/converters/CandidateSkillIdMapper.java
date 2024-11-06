package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillIdDto;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkillId;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CandidateSkillIdMapper {
    CandidateSkillId toEntity(CandidateSkillIdDto candidateSkillIdDto);

    CandidateSkillIdDto toDto(CandidateSkillId candidateSkillId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CandidateSkillId partialUpdate(CandidateSkillIdDto candidateSkillIdDto, @MappingTarget CandidateSkillId candidateSkillId);
}