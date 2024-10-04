package vn.edu.iuh.fit.donchung.backend.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.entities.Product;
import vn.edu.iuh.fit.donchung.backend.mapper.ProductPriceMapper;

@Mapper(uses = {ProductPriceMapper.class})
public interface ProductMapper {

    @Mapping(target = "productPrices", ignore = true)
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}