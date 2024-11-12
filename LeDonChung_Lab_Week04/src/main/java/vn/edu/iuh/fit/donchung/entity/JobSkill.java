package vn.edu.iuh.fit.donchung.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
@Setter
public class JobSkill {
    private Job job;
    private Skill skill;
    private int specific_level;
}
