package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.backend.entities.Post}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto implements Serializable {
    Long id;
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp publishedAt;
    String content;
    Set<PostCommentDto> postComments;
}