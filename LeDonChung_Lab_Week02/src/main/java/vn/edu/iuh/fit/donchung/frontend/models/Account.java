package vn.edu.iuh.fit.donchung.frontend.models;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Account {
    Long id;
    String address;
    LocalDateTime dob;
    String email;
    String fullName;
    String phone;
    EmployeeStatus status;
}
