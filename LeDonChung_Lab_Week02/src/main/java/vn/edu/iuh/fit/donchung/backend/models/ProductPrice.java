package vn.edu.iuh.fit.donchung.backend.models;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@NamedQueries(
        {
                @NamedQuery(name = "ProductPrice.findByProductId",
                        query = "SELECT pp FROM ProductPrice pp WHERE pp.product.id = :productId"),
                @NamedQuery(name = "ProductPrice.findLastByProductId",
                        query = "SELECT pp FROM ProductPrice pp WHERE pp.product.id = :productId ORDER BY pp.id.priceDateTime DESC")
        }
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
    @ToString.Exclude
    private Product product;

    // Getters và Setters
}