package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.backend.models.Employee;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Employee}
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