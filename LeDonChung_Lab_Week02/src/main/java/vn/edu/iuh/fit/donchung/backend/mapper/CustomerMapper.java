package vn.edu.iuh.fit.donchung.backend.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.donchung.backend.models.Customer;

@Mapper
public interface CustomerMapper {
    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "orders", ignore = true)
    Customer partialUpdate(CustomerDto customerDto, @MappingTarget Customer customer);
}