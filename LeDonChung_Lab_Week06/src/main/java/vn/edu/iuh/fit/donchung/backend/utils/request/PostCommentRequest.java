package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostCommentRequest {
    private Long post;
    private Long parent;
    private String title;
    private String content;
    private Boolean published;
}
