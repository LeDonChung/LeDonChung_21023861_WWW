package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.mapper;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;

public class RoleMapper {
    public RoleDto toDto(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
