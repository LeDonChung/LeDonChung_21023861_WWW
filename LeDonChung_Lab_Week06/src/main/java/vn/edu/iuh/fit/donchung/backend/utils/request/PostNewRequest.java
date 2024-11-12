package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostNewRequest {
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    String content;
    Long author;
    Long parent;
}
