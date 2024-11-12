package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SkillMapper.class, CandidateSkillIdMapper.class})
public interface CandidateSkillMapper {
    CandidateSkill toEntity(CandidateSkillDto candidateSkillDto);

    CandidateSkillDto toDto(CandidateSkill candidateSkill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CandidateSkill partialUpdate(CandidateSkillDto candidateSkillDto, @MappingTarget CandidateSkill candidateSkill);
}