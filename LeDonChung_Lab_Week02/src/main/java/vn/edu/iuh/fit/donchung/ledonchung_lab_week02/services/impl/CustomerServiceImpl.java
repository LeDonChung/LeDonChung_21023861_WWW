package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.impl;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Customer;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.CustomerRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.CustomerService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.utils.AppUtils;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        return customerRepository.delete(id);
    }
}
