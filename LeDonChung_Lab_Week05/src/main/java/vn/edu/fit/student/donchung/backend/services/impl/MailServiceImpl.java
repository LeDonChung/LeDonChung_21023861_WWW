package vn.edu.fit.student.donchung.backend.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.entities.Company;
import vn.edu.fit.student.donchung.backend.services.MailService;
import vn.edu.fit.student.donchung.backend.services.ThymeleafService;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private ThymeleafService thymeleafService;
    @Override
    public void senderForCandidate(Company company, String content, String to, String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper hepper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        hepper.setTo(to);
        hepper.setSubject(subject);

        // content
        Map<String, Object> variables = new HashMap<>();

        variables.put("content", content);
        variables.put("phone", company.getPhone());
        variables.put("email", company.getEmail());
        variables.put("webUrl", company.getWebUrl());
        variables.put("compName", company.getCompName());

        hepper.setText(thymeleafService.createContent("invitation-mail.html", variables), true);

        hepper.setFrom(email);

        mailSender.send(message);
    }
}
