package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Product;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPriceId;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.utils.AppUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            product = em.merge(product);
        }

        return product;
    }

    @Override
    public boolean updateStatus(Long id, ProductStatus status) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            product.setStatus(status);
            em.merge(product);
            return true;
        }
        return false;
    }
}
