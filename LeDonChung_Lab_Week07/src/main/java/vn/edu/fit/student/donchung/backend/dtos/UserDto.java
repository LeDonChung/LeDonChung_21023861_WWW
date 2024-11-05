package vn.edu.fit.student.donchung.backend.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    List<RoleDto> roles;

}