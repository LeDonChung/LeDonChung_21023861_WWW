package vn.edu.iuh.fit.donchung.backend.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;
import vn.edu.iuh.fit.donchung.backend.models.Order;

@Mapper(uses = {OrderDetailMapper.class, CustomerMapper.class, EmployeeMapper.class})
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}

