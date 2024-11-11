package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.backend.models.Product;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class)
                .setMaxResults(9)
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

    @Override
    public List<Product> findByStatus(ProductStatus status) {
        return em.createNamedQuery("Product.findByStatus", Product.class)
                .setParameter("status", status)
                .setMaxResults(8)
                .getResultList();
    }
}
