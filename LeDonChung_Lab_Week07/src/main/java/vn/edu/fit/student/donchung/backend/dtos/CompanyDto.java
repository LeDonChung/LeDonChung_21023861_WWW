package vn.edu.fit.student.donchung.backend.dtos;

import lombok.*;
import vn.edu.fit.student.donchung.backend.entities.Company;

import java.io.Serializable;

/**
 * DTO for {@link Company}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CompanyDto implements Serializable {
    Long id;
    String about;
    String email;
    String compName;
    String phone;
    String webUrl;
    AddressDto address;
}