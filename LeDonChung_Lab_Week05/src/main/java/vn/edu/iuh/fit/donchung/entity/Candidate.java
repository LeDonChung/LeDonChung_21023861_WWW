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
@Builder
public class Candidate {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Date dob;
    private Set<CandidateSkill> candidateSkills = new HashSet<>();

    public String getFirstName() {
        return fullName.split(" ")[0];
    }

    public String getLastName() {
        return fullName.split(" ")[2];
    }

    public String getMiddleName() {
        return fullName.split(" ")[1];
    }
}
