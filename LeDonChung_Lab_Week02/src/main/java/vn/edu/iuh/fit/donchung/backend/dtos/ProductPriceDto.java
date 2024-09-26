package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.models.ProductPrice;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ProductPrice}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductPriceDto implements Serializable {
    private Long productId;
    private LocalDateTime priceDateTime;
    private String note;
    private Double price;
}