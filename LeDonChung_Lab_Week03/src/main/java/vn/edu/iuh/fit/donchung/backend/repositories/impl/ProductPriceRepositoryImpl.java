package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.donchung.backend.entities.ProductPrice;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.donchung.backend.utils.SystemConstraints;

import java.util.List;

public class ProductPriceRepositoryImpl implements ProductPriceRepository {
    @PersistenceContext(unitName = SystemConstraints.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public ProductPrice findActiveByProductId(Long productId) {
        List<ProductPrice> productPrices = em.createNamedQuery("ProductPrice.findActiveByProductId", ProductPrice.class)
                .setParameter("productId", productId)
                .getResultList();
        return productPrices.isEmpty() ? null : productPrices.get(0);
    }
}
