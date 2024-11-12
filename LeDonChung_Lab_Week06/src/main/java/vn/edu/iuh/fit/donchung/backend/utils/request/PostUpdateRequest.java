package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostUpdateRequest {
    Long id;
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    String content;
    Long author;
    Long parent;
}
