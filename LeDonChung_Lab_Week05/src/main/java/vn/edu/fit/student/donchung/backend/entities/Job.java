package vn.edu.fit.student.donchung.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "job")
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Long id;

    @Column(name = "job_desc", nullable = false, length = 2000)
    private String jobDesc;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "com_id")
    private Company company;

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<JobSkill> jobSkills = new ArrayList<>();

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Mail> mails = new ArrayList<>();
}