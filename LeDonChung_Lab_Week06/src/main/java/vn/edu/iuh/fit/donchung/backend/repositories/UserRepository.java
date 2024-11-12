package vn.edu.iuh.fit.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.donchung.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
