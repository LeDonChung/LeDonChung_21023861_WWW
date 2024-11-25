package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;
import vn.edu.fit.student.donchung.backend.entities.Job;

import java.io.Serializable;
import java.util.ArrayList;
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
    CompanyDto company = new CompanyDto();
    List<JobSkillDto> jobSkills = new ArrayList<>();
}