package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.MailDto;
import vn.edu.fit.student.donchung.backend.entities.Mail;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MailMapper {
    @Mapping(target = "candidate.id", source = "candidateId")
    @Mapping(target = "job.id", source = "jobId")
    Mail toEntity(MailDto mailDto);

    @Mapping(target = "candidateId", source = "candidate.id")
    @Mapping(target = "jobId", source = "job.id")
    MailDto toDto(Mail mail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mail partialUpdate(MailDto mailDto, @MappingTarget Mail mail);
}