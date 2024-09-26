package vn.edu.iuh.fit.donchung.backend.repositories;

import vn.edu.iuh.fit.donchung.backend.models.ProductPrice;

import java.util.List;

public interface ProductPriceRepository {
    /**
     * Tìm giá sản phẩm theo id sản phẩm
     * @param productId id của sản phẩm
     * @return giá sản phẩm
     */
    List<ProductPrice> findByProductId(Long productId);


    /**
     * Lưu giá sản phẩm
     * @param productPrice giá sản phẩm
     * @return giá sản phẩm đã lưu
     */
    ProductPrice save(ProductPrice productPrice);

    /**
     * Tìm price có ngày bắt đầu cuối cùng của sản phẩm
     * @param productId id của sản phẩm
     * @return giá sản phẩm
     */
    ProductPrice findLastByProductId(Long productId);
}
