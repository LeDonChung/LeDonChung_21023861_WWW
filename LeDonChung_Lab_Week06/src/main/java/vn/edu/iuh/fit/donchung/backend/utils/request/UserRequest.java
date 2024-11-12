package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    Long id;
    String firstName;
    String middleName;
    String lastName;
    String mobile;
    String email;
    String intro;
    String profile;
}
