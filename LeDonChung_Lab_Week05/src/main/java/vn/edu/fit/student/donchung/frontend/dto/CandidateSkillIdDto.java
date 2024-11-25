package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.CandidateSkillId}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CandidateSkillIdDto implements Serializable {
    Long skillId;
    Long canId;
}