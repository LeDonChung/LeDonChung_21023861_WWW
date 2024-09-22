package vn.edu.iuh.fit.donchung.backend.mapper;

import org.mapstruct.Mapper;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.entities.Product;

public class ProductMapper {
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imgPath(product.getImgPath())
                .build();
    }
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .imgPath(productDto.getImgPath())
                .build();
    }
}
