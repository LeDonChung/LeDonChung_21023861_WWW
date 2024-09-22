package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductPriceDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPrice;

@Mapper(uses = ProductMapper.class)
public interface ProductPriceMapper {
    @Mapping(target = "productId", source = "id.productId")
    @Mapping(target = "priceDateTime", source = "id.priceDateTime")
    ProductPriceDto toDto(ProductPrice entity);

    @Mapping(target = "id.productId", source = "productId")
    @Mapping(target = "id.priceDateTime", source = "priceDateTime")
    @Mapping(target = "product", ignore = true)
    ProductPrice toEntity(ProductPriceDto dto);
}
