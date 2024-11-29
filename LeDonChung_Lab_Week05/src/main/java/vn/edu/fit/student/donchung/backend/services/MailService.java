package vn.edu.fit.student.donchung.backend.services;

import jakarta.mail.MessagingException;
import vn.edu.fit.student.donchung.backend.entities.Company;

public interface MailService {
    void senderForCandidate(Company company, String content, String to, String subject) throws MessagingException;
}
