package vn.edu.fit.student.donchung.backend.dtos;

import lombok.*;
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
@Builder
@ToString
public class CandidateDto implements Serializable {
    Long id;
    LocalDate dob;
    String email;
    String fullName;
    String phone;
    AddressDto address;
    List<CandidateSkillDto> candidateSkills;
    List<ExperienceDto> experiences;
}