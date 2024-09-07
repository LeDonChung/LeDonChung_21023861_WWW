package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Log}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogDto implements Serializable {
    Long id;
    String accountId;
    Instant loginTime;
    Instant logoutTime;
    String notes;
}