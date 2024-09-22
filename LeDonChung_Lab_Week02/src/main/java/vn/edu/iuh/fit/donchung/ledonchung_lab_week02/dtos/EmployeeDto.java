package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.EmployeeStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Employee}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeDto implements Serializable {
    Long id;
    String address;
    LocalDateTime dob;
    String email;
    String fullName;
    String phone;
    EmployeeStatus status;
}