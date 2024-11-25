package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.Experience}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExperienceDto implements Serializable {
    Long id;
    LocalDate toDate;
    LocalDate fromDate;
    String companyName;
    String role;
    String workDescription;
}