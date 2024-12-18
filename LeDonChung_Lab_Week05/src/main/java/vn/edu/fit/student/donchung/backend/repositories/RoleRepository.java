package vn.edu.fit.student.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fit.student.donchung.backend.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
    public Optional<Role> findByCode(String code);
}
