package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.entities.Product;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.backend.utils.SystemConstraints;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = SystemConstraints.PERSISTENCE_UNIT_NAME)
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
        if(product.getId() == null) {
            em.persist(product);
            em.flush();
        } else {
            product = em.merge(product);
        }
        return product;
    }
}
