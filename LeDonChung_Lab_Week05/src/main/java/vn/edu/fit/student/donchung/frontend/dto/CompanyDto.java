package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fit.student.donchung.backend.entities.Company;

import java.io.Serializable;

/**
 * DTO for {@link Company}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class CompanyDto extends UserDto implements Serializable {
    Long id;
    String about;
    String email;
    String compName;
    String phone;
    String webUrl;
    AddressDto address = new AddressDto();

    /**
     * Returns the full address in a formatted string.
     */
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();

        if (address.number != null && !address.number.isEmpty()) {
            fullAddress.append(address.number).append(", ");
        }
        if (address.street != null && !address.street.isEmpty()) {
            fullAddress.append(address.street).append(", ");
        }
        if (address.city != null && !address.city.isEmpty()) {
            fullAddress.append(address.city).append(", ");
        }
        if (address.country != null) {
            fullAddress.append(address.country.getName()).append(", ");
        }
        if (address.zipcode != null && !address.zipcode.isEmpty()) {
            fullAddress.append(address.zipcode);
        }

        if (!fullAddress.isEmpty() && fullAddress.charAt(fullAddress.length() - 1) == ' ') {
            fullAddress.setLength(fullAddress.length() - 2);
        }

        return fullAddress.toString();
    }
}