package vn.edu.iuh.fit.donchung.backend.utils.response;

import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String middleName;
    String lastName;
    String mobile;
    String email;
    Timestamp lastLogin;
    String intro;
    String profile;
}
