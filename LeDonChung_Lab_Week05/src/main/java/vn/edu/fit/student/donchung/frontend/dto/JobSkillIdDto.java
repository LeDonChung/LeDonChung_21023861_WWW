package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.JobSkillId}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobSkillIdDto implements Serializable {
    Long jobId;
    Long skillId;
}