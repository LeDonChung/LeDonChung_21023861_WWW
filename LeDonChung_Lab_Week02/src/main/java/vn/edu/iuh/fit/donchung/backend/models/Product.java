package vn.edu.iuh.fit.donchung.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.donchung.backend.enums.ProductStatus;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
                @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
                @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status")
        }

)
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductPrice> productPrices;

    // Getters và Setters
}