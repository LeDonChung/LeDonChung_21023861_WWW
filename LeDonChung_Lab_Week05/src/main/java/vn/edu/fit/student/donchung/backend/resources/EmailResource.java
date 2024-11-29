package vn.edu.fit.student.donchung.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fit.student.donchung.backend.dtos.MailDto;
import vn.edu.fit.student.donchung.backend.services.JobService;

@RestController
@RequestMapping("/api/emails")
public class EmailResource {
    @Autowired
    private JobService jobService;
    @PostMapping("/sendForCandidate")
    public ResponseEntity<Boolean> sendForCandidate(@RequestBody MailDto mailDto) {
        try {
            boolean status = jobService.sendMailToCandidate(mailDto);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
