package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.entities.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * DTO for {@link User}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto implements Serializable {
    Long id;
    String firstName;
    String middleName;
    String lastName;
    String mobile;
    String email;
    String passwordHash;
    Timestamp registeredAt;
    Timestamp lastLogin;
    String intro;
    String profile;
    Set<PostDto> posts;
}