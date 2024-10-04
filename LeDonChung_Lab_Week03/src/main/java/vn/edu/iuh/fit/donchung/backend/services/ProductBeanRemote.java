package vn.edu.iuh.fit.donchung.backend.services;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductPriceDto;

import java.util.List;

@Remote
public interface ProductBeanRemote {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto save(ProductDto productDto) throws Exception;
    ProductDto addPrice(Long productId, ProductPriceDto productPriceDto) throws Exception;
}
