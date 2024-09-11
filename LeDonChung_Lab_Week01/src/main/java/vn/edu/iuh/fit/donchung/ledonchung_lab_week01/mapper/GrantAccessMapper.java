package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.mapper;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessIdDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccessId;

public class GrantAccessMapper {
    @Inject
    private RoleMapper roleMapper;
//    @Inject
//    private AccountMapper accountMapper;
    public GrantAccessDto toDto(GrantAccess entity) {
        GrantAccessDto dto = new GrantAccessDto();

        GrantAccessIdDto grantAccessIdDto = new GrantAccessIdDto();
        grantAccessIdDto.setAccountId(entity.getId().getAccountId());
        grantAccessIdDto.setRoleId(entity.getId().getRoleId());
        dto.setId(grantAccessIdDto);

        dto.setIsGrant(entity.getIsGrant());
        dto.setNote(entity.getNote());
        dto.setRole(roleMapper.toDto(entity.getRole()));
//        dto.setAccount(accountMapper.toDto(entity.getAccount()));
        return dto;
    }

    public GrantAccess toEntity(GrantAccessDto grantAccessDto) {
        GrantAccess entity = new GrantAccess();

        GrantAccessId grantAccessId = new GrantAccessId();
        grantAccessId.setAccountId(grantAccessDto.getAccount().getAccountId());
        grantAccessId.setRoleId(grantAccessDto.getRole().getRoleId());

        entity.setId(grantAccessId);
        entity.setIsGrant(grantAccessDto.getIsGrant());
        entity.setNote(grantAccessDto.getNote());

        return entity;
    }
}
