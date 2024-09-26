package vn.edu.iuh.fit.donchung.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;

import java.util.List;

/**
 * Model for {@link vn.edu.iuh.fit.donchung.backend.models.Product}
 */
@Remote
public interface ProductModel {
    /**
     * Tìm tất cả sản phẩm có trạng thái active và giá mới nhất của sản phẩm
     * @return danh sách sản phẩm
     */
    List<ProductDto> findAll();

    ProductDto findById(Long id);
}
