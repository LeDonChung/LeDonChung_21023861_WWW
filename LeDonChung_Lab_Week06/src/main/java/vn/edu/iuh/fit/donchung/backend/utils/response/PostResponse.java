package vn.edu.iuh.fit.donchung.backend.utils.response;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.dtos.PostCommentDto;
import vn.edu.iuh.fit.donchung.backend.dtos.UserDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostResponse {
    Long id;
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp publishedAt;
    String content;
    UserResponse author;
    Long parent;
    Set<PostCommentResponse> postComments = new HashSet<>();
}