package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.models.OrderDetail;

import java.io.Serializable;

/**
 * DTO for {@link OrderDetail}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDetailDto implements Serializable {
    Long orderId;
    Long productId;
    String note;
    Double price;
    Double quantity;
}