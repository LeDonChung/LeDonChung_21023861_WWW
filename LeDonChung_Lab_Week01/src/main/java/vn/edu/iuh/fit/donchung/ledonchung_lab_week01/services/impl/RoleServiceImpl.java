package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.mapper.RoleMapper;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.RoleRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.RoleService;

import java.util.List;
import java.util.stream.Collectors;

public class RoleServiceImpl implements RoleService {
    @Inject
    private RoleRepository roleRepository;
    @Inject
    private RoleMapper roleMapper;

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(role -> roleMapper.toDto(role))
                .collect(Collectors.toList());
    }
}
