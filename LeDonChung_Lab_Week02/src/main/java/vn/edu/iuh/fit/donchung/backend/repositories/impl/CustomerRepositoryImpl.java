package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.models.Customer;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;
import vn.edu.iuh.fit.donchung.backend.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(em.find(Customer.class, id));
    }

    @Override
    public Customer save(Customer customer) {
        if(customer.getId() == null) {
            em.persist(customer);
        } else {
            Optional<Customer> c = findById(customer.getId());
            if(c.isPresent()) {
                Customer customerUpdate = c.get();
                customerUpdate.setName(customer.getName());
                customerUpdate.setAddress(customer.getAddress());
                customerUpdate.setEmail(customer.getEmail());
                customerUpdate.setPhone(customer.getPhone());
                customer = em.merge(customerUpdate);
            }
        }
        return customer;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> c = findById(id);
        if(c.isPresent()) {
            em.remove(c.get());
            return true;
        }
        return false;
    }
}
