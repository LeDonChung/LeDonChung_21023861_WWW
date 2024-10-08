package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.models.ProductImage;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductImageRepository;

import java.util.List;

@Transactional
public class ProductImageRepositoryImpl implements ProductImageRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<ProductImage> findByProductId(Long productId) {
        return em.createNamedQuery("ProductImage.findByProductId", ProductImage.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public ProductImage findById(Long id) {
        return em.find(ProductImage.class, id);
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        if (productImage.getId() == null) {
            em.persist(productImage);
        } else {
            productImage = em.merge(productImage);
        }

        return productImage;
    }
}
