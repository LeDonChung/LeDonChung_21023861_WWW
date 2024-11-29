package vn.edu.fit.student.donchung.backend.repositories;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.MailDto;
import vn.edu.fit.student.donchung.backend.entities.Mail;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MailMapper {
    @Mapping(source = "jobId", target = "job.id")
    @Mapping(source = "candidateId", target = "candidate.id")
    Mail toEntity(MailDto mailDto);

    @InheritInverseConfiguration(name = "toEntity")
    MailDto toDto(Mail mail);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mail partialUpdate(MailDto mailDto, @MappingTarget Mail mail);
}