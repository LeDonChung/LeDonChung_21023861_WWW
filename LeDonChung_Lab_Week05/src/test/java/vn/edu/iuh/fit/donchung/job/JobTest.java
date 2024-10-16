package vn.edu.iuh.fit.donchung.job;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.donchung.entity.Job;
import vn.edu.iuh.fit.donchung.entity.JobSkill;
import vn.edu.iuh.fit.donchung.entity.Skill;
import vn.edu.iuh.fit.donchung.repositories.JobRepository;
import vn.edu.iuh.fit.donchung.repositories.SkillRepository;

import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
public class JobTest {
    @Autowired
    private JobRepository jobRepository;

    private final Logger logger = Logger.getLogger(JobTest.class.getName());

    @Autowired
    private SkillRepository skillRepository;
    @Test
    void testInsert() {
        Job job = Job.builder()
                        .description("C# Developer")
                        .build();

        job = jobRepository.save(job);
        Job actual = jobRepository.findById(job.getId());
        logger.info("Insert Job: " + job);
        logger.info("Insert Actual: " + actual);
        assert job.getDescription().equals(actual.getDescription());
    }

    @Test
    void testUpdate() {
        Job job = Job.builder()
                        .id(1)
                        .description("Backend Developer")
                        .build();

        job = jobRepository.save(job);
        Job actual = jobRepository.findById(1);
        logger.info("Update Job: " + job);
        logger.info("Update Actual: " + actual);
        assert job.getDescription().equals(actual.getDescription());
    }

    @Test
    void testFindById() {
        Job job = jobRepository.findById(1);
        logger.info("Job: " + job);
        assert job.getId() == 1;
    }

    @Test
    void testDelete() {
        boolean result = jobRepository.delete(1);

        Job job = jobRepository.findById(1);
        logger.info("Job: " + job);
        assert job == null;
    }

    @Test
    void testFindAll() {
        List<Job> jobs = jobRepository.findAll();
        jobs.forEach(System.out::println);
    }


    @Test
    void testAddSkill() {
        Job job = jobRepository.findById(2);
        logger.info("Job: " + job);

        List<JobSkill> jobSkills = List.of(
                JobSkill.builder().job(job).skill(skillRepository.findById(2)).specific_level(4).build(),
                JobSkill.builder().job(job).skill(skillRepository.findById(4)).specific_level(3).build()
        );

        boolean result = jobRepository.addSkill(job, jobSkills);
        logger.info("Add Skill: " + result);
        assert result;
    }

    @Test
    void testFindCandidates() {
        Job job = jobRepository.findById(2);
        jobRepository.findCandidates(job.getId()).forEach(System.out::println);
    }

}
