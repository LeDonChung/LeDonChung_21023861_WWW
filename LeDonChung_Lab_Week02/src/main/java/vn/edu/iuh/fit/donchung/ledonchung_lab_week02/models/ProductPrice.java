package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;

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
public class ProductPrice {
    @EmbeddedId
    private ProductPriceId id;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    // Getters và Setters
}