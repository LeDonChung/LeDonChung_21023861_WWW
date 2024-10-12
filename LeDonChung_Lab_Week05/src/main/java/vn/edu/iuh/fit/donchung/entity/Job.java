package vn.edu.iuh.fit.donchung.entity;


import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Job {
    private Integer id;
    private String description;
    private Set<JobSkill> jobSkills = new HashSet<>();
}
