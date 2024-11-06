package vn.edu.fit.student.donchung.backend.dtos;

import lombok.*;
import vn.edu.fit.student.donchung.backend.entities.Job;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Job}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobDto implements Serializable {
    Long id;
    String jobDesc;
    String jobName;
    CompanyDto company;
    List<JobSkillDto> jobSkills;
}