package vn.edu.fit.student.donchung.backend.converters;

import org.mapstruct.*;
import vn.edu.fit.student.donchung.backend.dtos.CompanyDto;
import vn.edu.fit.student.donchung.backend.entities.Company;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, JobMapper.class, AddressMapper.class})
public interface CompanyMapper {
    Company toEntity(CompanyDto companyDto);

    CompanyDto toDto(Company company);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Company partialUpdate(CompanyDto companyDto, @MappingTarget Company company);
}