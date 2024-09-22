package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Product;

@Mapper(uses = {ProductImageMapper.class, ProductPriceMapper.class})
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkProductImages(@MappingTarget Product product) {
        product.getProductImages().forEach(productImage -> productImage.setProduct(product));
    }

    @AfterMapping
    default void linkProductPrices(@MappingTarget Product product) {
        product.getProductPrices().forEach(productPrice -> productPrice.setProduct(product));
    }

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}