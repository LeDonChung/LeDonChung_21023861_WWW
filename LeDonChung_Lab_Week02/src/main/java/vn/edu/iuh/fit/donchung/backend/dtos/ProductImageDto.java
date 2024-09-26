package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.models.ProductImage;

import java.io.Serializable;

/**
 * DTO for {@link ProductImage}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductImageDto implements Serializable {
    private Long id;
    private String alternative;
    private String path;
    private Long productId;
}