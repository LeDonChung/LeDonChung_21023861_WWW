package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.impl;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.RoleRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils.AppUtils;

import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public List<Role> findAll() {
        try(var em = AppUtils.getEntityManager()) {
            return em.createNamedQuery("Role.findAll", Role.class)
                    .getResultList();
        }
    }
}
