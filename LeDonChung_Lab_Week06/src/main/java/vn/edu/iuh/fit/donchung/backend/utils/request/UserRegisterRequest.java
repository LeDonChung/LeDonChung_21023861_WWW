package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterRequest {
    String firstName;
    String middleName;
    String lastName;
    String mobile;
    String email;
    String passwordRaw;
    String intro;
    String profile;
}
