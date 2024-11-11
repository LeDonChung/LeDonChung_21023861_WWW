package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductDto implements Serializable {
    private Long id;
    private String description;
    private String manufacturer;
    private String name;
    private String unit;
    private String imgPath;
    private Double price;

    public String getStringPrice() {
        return String.format("%.2f", price);
    }
}