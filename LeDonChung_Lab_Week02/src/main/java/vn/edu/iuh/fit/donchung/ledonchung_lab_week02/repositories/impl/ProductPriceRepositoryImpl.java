package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPrice;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.utils.AppUtils;

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
    public ProductPrice findById(Long id) {
        return em.find(ProductPrice.class, id);
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
