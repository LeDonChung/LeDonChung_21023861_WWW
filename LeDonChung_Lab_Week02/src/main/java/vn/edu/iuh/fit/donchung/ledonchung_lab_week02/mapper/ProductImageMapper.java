package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductImageDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductImage;

@Mapper
public interface ProductImageMapper {
    @Mapping(target = "productId", source = "product.id")
    ProductImageDto toDto(ProductImage entity);
    @Mapping(target = "product.id", source = "productId")
    ProductImage toEntity(ProductImageDto dto);

}
