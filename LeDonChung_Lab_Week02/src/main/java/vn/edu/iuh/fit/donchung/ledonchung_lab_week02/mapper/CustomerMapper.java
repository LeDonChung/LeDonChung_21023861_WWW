package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.CustomerDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Customer;

@Mapper
public interface CustomerMapper {
    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "orders", ignore = true)
    Customer partialUpdate(CustomerDto customerDto, @MappingTarget Customer customer);
}