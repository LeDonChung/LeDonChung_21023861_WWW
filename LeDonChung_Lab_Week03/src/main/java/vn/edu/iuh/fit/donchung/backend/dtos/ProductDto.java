package vn.edu.iuh.fit.donchung.backend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.backend.entities.Product}
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 150)
    String name;
    @NotNull
    String description;
    @Size(max = 250)
    String imgPath;
}