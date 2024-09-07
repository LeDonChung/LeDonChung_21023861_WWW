package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccessId}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrantAccessIdDto implements Serializable {
    String roleId;
    String accountId;
}