package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.models.Customer;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CustomerDto implements Serializable {
    private Long id;
    private String address;
    private String name;
    private String email;
    private String phone;
}