package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@NamedQueries(
        @NamedQuery(name = "ProductPrice.findByProductId",
                query = "SELECT pp FROM ProductPrice pp WHERE pp.product.id = :productId")
)
public class ProductPrice {
    @EmbeddedId
    private ProductPriceId id;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    // Getters và Setters
}