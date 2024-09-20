package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@Builder
public class OrderDetailId implements Serializable {
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;

    public OrderDetailId() {
    }

    public OrderDetailId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
