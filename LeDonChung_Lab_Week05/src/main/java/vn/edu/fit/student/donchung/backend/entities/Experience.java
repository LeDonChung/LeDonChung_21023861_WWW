package vn.edu.fit.student.donchung.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate candidate;

    @Column(name = "from_date")
    private LocalDate fromDate;

    private String companyName;

    private String role;

    @Column(name = "work_desc")
    private String workDescription;
}
