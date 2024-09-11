package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;

import java.util.List;

public interface RoleRepository {
    public List<Role> findAll();
}
