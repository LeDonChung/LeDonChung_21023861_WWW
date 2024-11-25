package vn.edu.fit.student.donchung.frontend.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;
import vn.edu.fit.student.donchung.backend.entities.Address;

import java.io.Serializable;

/**
 * DTO for {@link Address}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddressDto implements Serializable {
    Long id;
    String street;
    String city;
    CountryCode country;
    String number;
    String zipcode;
}