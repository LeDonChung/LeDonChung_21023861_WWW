package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Employee;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.EmployeeRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateStatus(Long id, EmployeeStatus status) {
        return employeeRepository.updateStatus(id, status);
    }
}
