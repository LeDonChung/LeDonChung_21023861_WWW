package vn.edu.iuh.fit.donchung.backend.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

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
                        query = "SELECT pp FROM ProductPrice pp WHERE pp.product.id = :productId ORDER BY pp.id.priceDateTime DESC"),
                @NamedQuery(name = "ProductPrice.findActiveByProductId",
                        query = "SELECT pp FROM ProductPrice pp WHERE pp.product.id = :productId AND pp.status = 1")
        }
)
public class ProductPrice {
    @EmbeddedId
    private ProductPriceId id;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "price")
    private Double value;

    /**
     *
     * 0: Chưa áp dụng
     * 1: Đang áp dụng
     *
     */
    private Integer status;

    @Column(name = "apply_date")
    private Timestamp applyDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    @ToString.Exclude
    private Product product;

    // Getters và Setters
}