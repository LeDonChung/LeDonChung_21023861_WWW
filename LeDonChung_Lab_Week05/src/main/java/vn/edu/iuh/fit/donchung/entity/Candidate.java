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
        int length = fullName.split(" ").length;
        if(length > 0)
            return fullName.split(" ")[0];
        return "";
    }

    public String getLastName() {
        int length = fullName.split(" ").length;
        if(length == 3)
            return fullName.split(" ")[2];
        return "";
    }

    public String getMiddleName() {
        int length = fullName.split(" ").length;
        if(length > 1)
            return fullName.split(" ")[1];
        return "";
    }
}
