package vn.edu.iuh.fit.donchung.backend.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.backend.models.Employee;
import vn.edu.iuh.fit.donchung.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.donchung.backend.mapper.EmployeeMapper;
import vn.edu.iuh.fit.donchung.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.donchung.backend.services.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private EmployeeMapper employeeMapper;
    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto).orElse(null);
    }

    @Override
    public EmployeeDto save(EmployeeDto employee) {
        Employee entity = employeeMapper.toEntity(employee);
        if (entity.getId() == null) {
            entity.setStatus(EmployeeStatus.ACTIVE);
        } else {
            Employee oldEmployee = employeeRepository.findById(employee.getId()).orElse(null);
            if (oldEmployee != null) {
                entity = employeeMapper.partialUpdate(employee, oldEmployee);
            }
        }
        return employeeMapper.toDto(employeeRepository.save(entity));
    }

    @Override
    public EmployeeDto updateStatus(Long id, EmployeeStatus status) {
        return employeeMapper.toDto(employeeRepository.updateStatus(id, status));
    }

    @Override
    public EmployeeDto getByPhone(String phone) {
        return employeeRepository.findByPhone(phone).map(employeeMapper::toDto).orElse(null);
    }
}
