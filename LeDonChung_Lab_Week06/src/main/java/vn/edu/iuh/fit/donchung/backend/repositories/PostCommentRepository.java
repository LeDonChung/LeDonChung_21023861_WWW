package vn.edu.iuh.fit.donchung.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.donchung.backend.entities.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}