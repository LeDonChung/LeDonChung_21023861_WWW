package vn.edu.iuh.fit.donchung.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductImageDto;
import vn.edu.iuh.fit.donchung.backend.models.ProductImage;

@Mapper
public interface ProductImageMapper {
    @Mapping(target = "productId", source = "product.id")
    ProductImageDto toDto(ProductImage entity);
    @Mapping(target = "product.id", source = "productId")
    ProductImage toEntity(ProductImageDto dto);

}
