package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.models.ProductPrice;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;

import java.util.List;
@Transactional
public class ProductPriceRepositoryImpl implements ProductPriceRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<ProductPrice> findByProductId(Long productId) {
        return em.createNamedQuery("ProductPrice.findByProductId", ProductPrice.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public ProductPrice findLastByProductId(Long productId) {
        List<ProductPrice> list = em.createNamedQuery("ProductPrice.findLastByProductId", ProductPrice.class)
                .setParameter("productId", productId)
                .setMaxResults(1)
                .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public ProductPrice save(ProductPrice productPrice) {
        if (productPrice.getId() == null) {
            em.persist(productPrice);
        } else {
            productPrice = em.merge(productPrice);
        }

        return productPrice;
    }
}
