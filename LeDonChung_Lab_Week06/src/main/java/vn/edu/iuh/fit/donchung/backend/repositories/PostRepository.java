package vn.edu.iuh.fit.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.donchung.backend.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}