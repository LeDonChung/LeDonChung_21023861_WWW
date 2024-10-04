package vn.edu.iuh.fit.donchung.backend.repositories;

import vn.edu.iuh.fit.donchung.backend.entities.ProductPrice;

public interface ProductPriceRepository {
    ProductPrice findActiveByProductId(Long productId);
}
