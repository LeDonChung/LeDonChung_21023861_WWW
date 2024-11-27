package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.entities.Candidate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, CandidateSkillMapper.class, ExperienceMapper.class, AddressMapper.class})
public interface CandidateMapper {
    Candidate toEntity(CandidateDto candidateDto);

    CandidateDto toDto(Candidate candidate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "candidateSkills", ignore = true)
    Candidate partialUpdate(CandidateDto candidateDto, @MappingTarget Candidate candidate);
}