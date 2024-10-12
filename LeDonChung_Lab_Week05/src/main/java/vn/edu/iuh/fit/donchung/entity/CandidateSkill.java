package vn.edu.iuh.fit.donchung.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CandidateSkill {
    private Candidate candidate;
    private Skill skill;
    private int level;
}
