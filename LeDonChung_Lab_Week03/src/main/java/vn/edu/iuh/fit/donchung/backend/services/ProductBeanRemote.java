package vn.edu.iuh.fit.donchung.backend.services;

import jakarta.ejb.Local;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;

import java.util.List;

@Local
public interface ProductBeanRemote {
    List<ProductDto> getAll();
    ProductDto getById(Integer id);
    ProductDto save(ProductDto productDto);
}
