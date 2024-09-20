package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    // Getters và Setters
}
