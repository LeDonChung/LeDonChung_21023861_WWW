package vn.edu.iuh.fit.donchung.backend.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDetailDto;
import vn.edu.iuh.fit.donchung.backend.models.OrderDetail;

@Mapper(uses = {OrderMapper.class, ProductMapper.class})
public interface OrderDetailMapper {
    @Mapping(target = "id.orderId", source = "orderId")
    @Mapping(target = "id.productId", source = "productId")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderDetail toEntity(OrderDetailDto orderDetailDto);

    @Mapping(target = "orderId", source = "id.orderId")
    @Mapping(target = "productId", source = "id.productId")
    @Mapping(target = "product", source = "product")
    OrderDetailDto toDto(OrderDetail orderDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetail partialUpdate(OrderDetailDto orderDetailDto, @MappingTarget OrderDetail orderDetail);
}