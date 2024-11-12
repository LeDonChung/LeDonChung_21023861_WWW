package vn.edu.iuh.fit.donchung.backend.utils.response;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.dtos.PostDto;
import vn.edu.iuh.fit.donchung.backend.entities.PostComment;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DTO for {@link PostComment}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostCommentResponse implements Serializable {
    Long id;
    String title;
    Boolean published;
    Timestamp createdAt;
    Timestamp publishedAt;
    String content;
    Long parent;
    Long post;
    Set<PostCommentResponse> postComments = new LinkedHashSet<>();
}