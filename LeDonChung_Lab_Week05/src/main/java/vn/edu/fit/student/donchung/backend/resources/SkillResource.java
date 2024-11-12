package vn.edu.fit.student.donchung.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fit.student.donchung.backend.dtos.SkillDto;
import vn.edu.fit.student.donchung.backend.services.SkillService;

import java.util.List;

@RequestMapping("/api/skills")
@RestController
public class SkillResource {
    @Autowired
    private SkillService skillService;
    @GetMapping
    public ResponseEntity<List<SkillDto>> getAll() throws Exception {
        try {
            return ResponseEntity.ok(
                    skillService.getAllList()
            );
        } catch (Exception e) {
            throw new Exception(
                    "Error: " + e.getMessage(),
                    e
            );
        }
    }
}
