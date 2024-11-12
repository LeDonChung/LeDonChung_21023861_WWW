package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.backend.entities.PostComment}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostCommentDto implements Serializable {
    Long id;
    String title;
    Boolean published;
    Timestamp createdAt;
    Timestamp publishedAt;
    String content;
}