package vn.edu.fit.student.donchung.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mails_logs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Mail {
    @Id
    @Column(name = "mail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "can_id")
    private Candidate candidate;

    @Column(name = "to_email")
    private String to;

    @Column(name = "subject")
    private String subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private Job job;
}
