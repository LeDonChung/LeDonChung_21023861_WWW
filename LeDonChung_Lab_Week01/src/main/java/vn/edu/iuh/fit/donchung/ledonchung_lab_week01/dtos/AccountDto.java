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
    public String accountId;
    public String fullName;
    public String password;
    public String email;
    public String phone;
    public Byte status;
    public Set<GrantAccessDto> grantAccesses = new LinkedHashSet<>();
    public boolean isRole(String roleId) {
        for (GrantAccessDto ga : grantAccesses) {
            if (ga.getRole().getRoleId().equals(roleId)) {
                return true;
            }
        }
        return false;
    }

}