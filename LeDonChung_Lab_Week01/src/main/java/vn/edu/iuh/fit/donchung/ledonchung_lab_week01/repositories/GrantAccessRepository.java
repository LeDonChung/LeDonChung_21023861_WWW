package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess;

public interface GrantAccessRepository {
    public GrantAccess findByRoleIdAndAccountId(String roleId, String accountId);
    public GrantAccess save(GrantAccess grantAccess);
}
