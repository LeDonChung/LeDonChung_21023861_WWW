package vn.edu.fit.student.donchung.backend.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fit.student.donchung.backend.entities.Candidate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Candidate}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class CandidateDto extends UserDto implements Serializable {
    Long id;
    LocalDate dob;
    String email;
    String fullName;
    String phone;
    AddressDto address;
    List<CandidateSkillDto> candidateSkills;
    List<ExperienceDto> experiences;
    int match;
}