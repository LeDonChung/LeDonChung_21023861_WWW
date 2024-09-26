package vn.edu.iuh.fit.donchung.backend.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.backend.models.Customer;
import vn.edu.iuh.fit.donchung.backend.services.CustomerService;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.donchung.backend.mapper.CustomerMapper;
import vn.edu.iuh.fit.donchung.backend.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerMapper::toDto).orElse(null);
    }

    @Override
    public CustomerDto save(CustomerDto customer) {
        Customer customerNew = customerMapper.toEntity(customer);
        if(customerNew.getId() != null){
            Customer oldCustomer = customerRepository.findById(customerNew.getId()).orElse(null);
            if(oldCustomer != null){
                customerNew = customerMapper.partialUpdate(customer, oldCustomer);
            }
        }

        customerNew = customerRepository.save(customerNew);

        return customerMapper.toDto(customerNew);
    }

    @Override
    public boolean delete(Long id) {
        return customerRepository.delete(id);
    }
}
