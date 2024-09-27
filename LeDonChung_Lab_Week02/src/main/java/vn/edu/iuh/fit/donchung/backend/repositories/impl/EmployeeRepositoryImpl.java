package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.backend.models.Employee;
import vn.edu.iuh.fit.donchung.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;

import java.util.List;
import java.util.Optional;

@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public Employee save(Employee employee) {
        if(employee.getId() == null) {
            em.persist(employee);
        } else {
            Optional<Employee> e = findById(employee.getId());
            if(e.isPresent()) {
                Employee employeeUpdate = e.get();
                employeeUpdate.setFullName(employee.getFullName());
                employeeUpdate.setAddress(employee.getAddress());
                employeeUpdate.setEmail(employee.getEmail());
                employeeUpdate.setPhone(employee.getPhone());
                employeeUpdate.setStatus(employee.getStatus());
                employeeUpdate.setDob(employee.getDob());
                employee = em.merge(employeeUpdate);
            }
        }
        return employee;
    }

    @Override
    public Employee updateStatus(Long id, EmployeeStatus status) {
        Optional<Employee> e = findById(id);
        if(!e.isPresent()) {
            return null;
        }

        Employee employee = e.get();
        employee.setStatus(status);
        return em.merge(employee);
    }

    @Override
    public Optional<Employee> findByPhone(String phone) {
        List<Employee> employees = em.createNamedQuery("Employee.findByPhoneAndStatus", Employee.class)
                .setParameter("phone", phone)
                .setParameter("status", EmployeeStatus.ACTIVE)
                .getResultList();
        return !employees.isEmpty() ? Optional.of(employees.get(0)) : Optional.empty();
    }
}
