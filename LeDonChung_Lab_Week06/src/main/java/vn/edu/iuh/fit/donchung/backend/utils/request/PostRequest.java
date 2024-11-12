package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostRequest {
    Long id;
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp publishedAt;
    String content;
    Long author;
    Long parent;
}
