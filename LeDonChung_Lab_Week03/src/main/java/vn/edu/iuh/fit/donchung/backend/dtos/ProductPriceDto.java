package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
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
    private Double value;
    private Timestamp applyDate;
    private Integer status;
}