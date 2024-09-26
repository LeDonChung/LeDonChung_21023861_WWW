package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.models.Order;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;
import vn.edu.iuh.fit.donchung.backend.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<Order> findAll() {
        return em.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = em.createNamedQuery("Order.findById", Order.class)
                        .setParameter("id", id)
                        .getResultList()
                        .stream()
                        .findFirst();

        return order.orElse(null);
    }

    @Override
    public Order save(Order order) {
        if(order.getId() == null) {
            em.persist(order);
        } else {
            order = em.merge(order);
        }
        return order;
    }
}
