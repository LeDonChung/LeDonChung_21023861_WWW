package vn.edu.iuh.fit.donchung.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Skill {
    private Integer id;
    private String skillName;
    private String description;
    private String field;

}
