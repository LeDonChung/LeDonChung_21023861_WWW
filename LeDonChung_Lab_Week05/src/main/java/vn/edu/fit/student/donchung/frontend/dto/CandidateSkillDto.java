package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;
import vn.edu.fit.student.donchung.backend.entities.CandidateSkill;
import vn.edu.fit.student.donchung.backend.enums.SkillLevel;

import java.io.Serializable;

/**
 * DTO for {@link CandidateSkill}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CandidateSkillDto implements Serializable {
    CandidateSkillIdDto id;
    SkillDto skill;
    String moreInfos;
    SkillLevel skillLevel;
}