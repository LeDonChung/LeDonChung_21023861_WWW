package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos;

import lombok.*;
import lombok.Value;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrantAccessDto implements Serializable {
    GrantAccessIdDto id;
    RoleDto role;
    AccountDto account;
    Boolean isGrant;
    String note;
}