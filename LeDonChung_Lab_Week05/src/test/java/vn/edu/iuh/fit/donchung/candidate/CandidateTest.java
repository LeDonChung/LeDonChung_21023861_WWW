package vn.edu.iuh.fit.donchung.candidate;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.repositories.CandidateRepository;

import java.sql.Date;
import java.util.logging.Logger;

@SpringBootTest
public class CandidateTest {
    @Autowired
    private CandidateRepository candidateRepository;

    private final Logger logger = Logger.getLogger(CandidateTest.class.getName());


    @Test
    void testInsert() {
        Candidate candidate =
                Candidate.builder()
                        .fullName("demo")
                        .email("donle2@gmail.com")
                        .phone("0867713557")
                        .address("44/10 ")
                        .dob(Date.valueOf("1999-01-01"))
                        .build();

        candidate = candidateRepository.save(candidate);
        Candidate actual = candidateRepository.findById(candidate.getId());
        logger.info("Candidate: " + candidate);
        logger.info("Actual: " + actual);
        assert candidate.getEmail().equals(actual.getEmail());
    }

    @Test
    void testUpdate() {
        Candidate candidate =
                Candidate.builder()
                        .id(8)
                        .fullName("Le Don Chung")
                        .email("demo124@gmail.com")
                        .phone("0867713557")
                        .address("44/10 ")
                        .dob(Date.valueOf("1999-01-01"))
                        .build();

        candidate = candidateRepository.save(candidate);
        Candidate actual = candidateRepository.findById(8);
        logger.info("Candidate: " + candidate);
        assert candidate.getEmail().equals(actual.getEmail());
    }

    @Test
    void testFindById() {
        Candidate candidate = candidateRepository.findById(8);
        logger.info("Candidate: " + candidate);
        assert candidate.getId() == 8;
    }

    @Test
    void testDelete() {
        boolean result = candidateRepository.delete(8);

        Candidate candidate = candidateRepository.findById(8);
        logger.info("Candidate: " + candidate);
        logger.info("Delete: " + result);

        assert candidate == null;
    }

    @Test
    void testFindAll() {
        candidateRepository.findAll().forEach(candidate -> logger.info("Candidate: " + candidate));
    }
}
