package vn.edu.iuh.fit.donchung.frontend.models;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CartDetail {
    ProductDto productDto;
    String note;
    Double price;
    Long quantity;
}
