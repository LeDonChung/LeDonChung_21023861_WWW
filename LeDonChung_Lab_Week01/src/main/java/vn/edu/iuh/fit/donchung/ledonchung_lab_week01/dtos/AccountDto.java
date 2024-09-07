package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto implements Serializable {
    private String accountId;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private Byte status;
    private Set<GrantAccessDto> grantAccesses = new LinkedHashSet<>();
}