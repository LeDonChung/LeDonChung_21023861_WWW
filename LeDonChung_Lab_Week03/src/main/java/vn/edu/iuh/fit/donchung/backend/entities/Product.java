package vn.edu.iuh.fit.donchung.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "unit", length = 25)
    private String unit;

    @Column(name = "img_path", length = 250)
    private String imgPath;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductPrice> productPrices;

    // Getters và Setters
}