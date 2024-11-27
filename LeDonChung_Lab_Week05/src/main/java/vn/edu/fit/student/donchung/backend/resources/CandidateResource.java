package vn.edu.fit.student.donchung.backend.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fit.student.donchung.backend.dtos.CandidateDto;
import vn.edu.fit.student.donchung.backend.dtos.CandidateSkillDto;
import vn.edu.fit.student.donchung.backend.services.CandidateService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/candidates")
public class CandidateResource {
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/{candidateId}/skills")
    public ResponseEntity<List<CandidateSkillDto>> getSkillsByCandidateId(@PathVariable Long candidateId) {
        try {
            List<CandidateSkillDto> skills = candidateService.getSkillsByCandidateId(candidateId);
            return ResponseEntity.ok(skills);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDto> getByCategoryId(@PathVariable Long candidateId) {
        try {
            CandidateDto candidate = candidateService.getCandidateId(candidateId);
            return ResponseEntity.ok(candidate);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CandidateDto> updateCandidate(@RequestBody CandidateDto candidate) {
        try {
            CandidateDto updatedCandidate = candidateService.updateCandidate(candidate);
            return ResponseEntity.ok(updatedCandidate);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
