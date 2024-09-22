package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductImageDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductPriceDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Product;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Product}
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
    private ProductStatus status;
    private String unit;
    private List<ProductImageDto> productImages;
    private List<ProductPriceDto> productPrices;
}