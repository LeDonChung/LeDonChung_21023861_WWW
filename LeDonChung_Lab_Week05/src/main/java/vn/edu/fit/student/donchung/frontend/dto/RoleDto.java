package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.Role}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoleDto implements Serializable {
    Long id;
    String name;
    String code;
}