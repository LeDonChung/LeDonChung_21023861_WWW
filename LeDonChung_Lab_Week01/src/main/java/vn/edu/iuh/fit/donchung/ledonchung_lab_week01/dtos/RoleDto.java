package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDto implements Serializable {
    String roleId;
    String roleName;
    String description;
    Byte status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDto roleDto = (RoleDto) o;

        return roleId.equals(roleDto.roleId);
    }

    @Override
    public int hashCode() {
        return roleId.hashCode();
    }
}