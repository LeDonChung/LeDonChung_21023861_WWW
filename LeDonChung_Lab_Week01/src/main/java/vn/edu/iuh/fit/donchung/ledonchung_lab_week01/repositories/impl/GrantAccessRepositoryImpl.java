package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.impl;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils.AppUtils;

import java.util.List;

public class GrantAccessRepositoryImpl implements GrantAccessRepository {
    @Override
    public GrantAccess findByRoleIdAndAccountId(String roleId, String accountId) {
        try(var em = AppUtils.getEntityManager()) {
            List<GrantAccess> resultList = em.createNamedQuery("GrantAccess.findByRoleIdAndAccountId", GrantAccess.class)
                    .setParameter("roleId", roleId)
                    .setParameter("accountId", accountId)
                    .getResultList();

            return resultList.isEmpty() ? null : resultList.get(0);
        }
    }

    @Override
    public GrantAccess save(GrantAccess grantAccess) {
        GrantAccess grantTemp = findByRoleIdAndAccountId(grantAccess.getId().getRoleId(), grantAccess.getId().getAccountId());
        if (grantTemp == null) {
            try(var em = AppUtils.getEntityManager()) {
                em.getTransaction().begin();
                String query = "INSERT INTO grant_access (account_id, role_id, is_grant) VALUES (?, ?, ?)";
                em.createNativeQuery(query)
                        .setParameter(1, grantAccess.getId().getAccountId())
                        .setParameter(2, grantAccess.getId().getRoleId())
                        .setParameter(3, (byte)1)
                        .executeUpdate();
                em.getTransaction().commit();
                return grantAccess;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try(var em = AppUtils.getEntityManager()) {
                em.getTransaction().begin();
                String query = "UPDATE grant_access SET note = ?, account_id = ?, role_id = ? WHERE account_id = ? AND role_id = ?";
                em.createNativeQuery(query)
                        .setParameter(1, grantAccess.getNote())
                        .setParameter(2, grantAccess.getId().getAccountId())
                        .setParameter(3, grantAccess.getId().getRoleId())
                        .setParameter(4, grantAccess.getId().getAccountId())
                        .setParameter(5, grantAccess.getId().getRoleId())
                        .executeUpdate();
                em.getTransaction().commit();
                return grantAccess;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return grantTemp;
    }
}
