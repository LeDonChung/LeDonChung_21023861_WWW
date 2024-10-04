package vn.edu.iuh.fit.donchung.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductPriceDto;

import java.util.List;

@Remote
public interface ProductModel {
    List<ProductDto> findAll();
    ProductDto addPrice(Long productId, ProductPriceDto productPriceDto);

    ProductDto findById(Long id);

    ProductDto save(ProductDto productDto);
}
