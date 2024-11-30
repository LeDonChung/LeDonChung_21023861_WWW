package vn.edu.fit.student.donchung.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fit.student.donchung.backend.dtos.MailDto;
import vn.edu.fit.student.donchung.backend.entities.Company;
import vn.edu.fit.student.donchung.backend.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyResource {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('COMPANY')")
    public ResponseEntity<List<Company>> getAll() throws Exception {
        try {
            return ResponseEntity.ok(
                    companyService.getAllList()
            );
        } catch (Exception e) {
            throw new Exception(
                    "Error: " + e.getMessage(),
                    e
            );
        }
    }

    @GetMapping("/{companyId}/emails")
    public ResponseEntity<List<MailDto>> getEmails(@PathVariable Long companyId) throws Exception {
        try {
            return ResponseEntity.ok(
                    companyService.getAllEmails(companyId)
            );
        } catch (Exception e) {
            throw new Exception(
                    "Error: " + e.getMessage(),
                    e
            );
        }
    }
}
