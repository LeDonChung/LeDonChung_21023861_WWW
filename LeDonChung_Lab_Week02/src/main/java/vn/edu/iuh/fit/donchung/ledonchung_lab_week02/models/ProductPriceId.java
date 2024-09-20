package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
@Builder
public class ProductPriceId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "price_date_time")
    private LocalDateTime priceDateTime;


    public ProductPriceId() {
    }

    public ProductPriceId(Long productId, LocalDateTime priceDateTime) {
        this.productId = productId;
        this.priceDateTime = priceDateTime;
    }
}
