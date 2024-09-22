package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductImage;

import java.util.List;

public interface ProductImageRepository {
    /**
     * Tìm ảnh sản phẩm theo id sản phẩm
     * @param productId id của sản phẩm
     * @return danh sách ảnh sản phẩm
     */
    List<ProductImage> findByProductId(Long productId);

    ProductImage findById(Long id);

    ProductImage save(ProductImage productImage);
}
