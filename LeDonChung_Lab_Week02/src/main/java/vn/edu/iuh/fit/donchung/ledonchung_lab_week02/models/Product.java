package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.ProductStatus;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "manufacturer_name", length = 100)
    private String manufacturer;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;

    @Column(name = "unit", length = 25)
    private String unit;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices;

    // Getters và Setters
}