package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.entities.PostComment;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

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
    PostCommentDto parent;
    PostDto post;
    Set<PostComment> postComments = new LinkedHashSet<>();
}