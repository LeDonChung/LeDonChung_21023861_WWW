package vn.edu.fit.student.donchung.frontend.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.fit.student.donchung.backend.entities.Mail}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MailDto implements Serializable {
    Long id;
    String content;
    Long candidateId;
    String subject;
    String to;
    Long jobId;
}