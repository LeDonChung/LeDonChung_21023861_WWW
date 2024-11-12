package vn.edu.iuh.fit.donchung.backend.utils.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequest {
    private String phone;
    private String passwordRaw;
}
