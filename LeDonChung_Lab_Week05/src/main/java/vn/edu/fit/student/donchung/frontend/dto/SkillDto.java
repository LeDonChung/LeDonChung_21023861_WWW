package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;
import vn.edu.fit.student.donchung.backend.enums.SkillType;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.Skill}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillDto implements Serializable {
    Long id;
    String skillDescription;
    String skillName;
    SkillType type;
}