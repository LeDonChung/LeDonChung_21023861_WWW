package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;
import vn.edu.fit.student.donchung.backend.entities.JobSkill;
import vn.edu.fit.student.donchung.backend.enums.SkillLevel;

import java.io.Serializable;

/**
 * DTO for {@link JobSkill}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobSkillDto implements Serializable {
    JobSkillIdDto id;
    SkillDto skill;
    String moreInfos;
    SkillLevel skillLevel;
}