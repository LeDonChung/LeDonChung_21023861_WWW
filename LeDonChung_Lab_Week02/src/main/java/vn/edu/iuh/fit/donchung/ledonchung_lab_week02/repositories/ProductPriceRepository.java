package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPrice;

import java.util.List;

public interface ProductPriceRepository {
    /**
     * Tìm giá sản phẩm theo id sản phẩm
     * @param productId id của sản phẩm
     * @return giá sản phẩm
     */
    List<ProductPrice> findByProductId(Long productId);
    ProductPrice findById(Long id);

    ProductPrice save(ProductPrice productPrice);
}
