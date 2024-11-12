## 📝Nội Dung Dựa Án Của Tôi
- Tên dự án: LeDonChung_Lab_Week04
- Công nghệ: Springboot, Làm quen với JdbcTemplate
- Người hướng dẫn: Võ Văn Hải
- Người thực hiện: Lê Đôn Chủng
- Mã số sinh viên: 21023861
  
# I. Giới thiệu

Làm quen với JdbcTemplate bằng cách CRUD Candidate, Skill, Job

# II. Cách cài đặt

Để cài đặt ứng dụng, bạn có thể làm theo các bước dưới đây:

1. Clone repository về máy của bạn:

   ```bash
   https://github.com/LeDonChung/LeDonChung_21023861_WWW.git
   ```
2. Di chuyển tới thư mục LeDonChung_Lab_Week04:

   ```bash
   cd LeDonChung_Lab_Week04
   mvn install
   ```
3. Chạy ứng dụng:
   ```bash
   mvn spring-boot:run
   ```
4. Truy cập qua địa chỉ
    ```
    http://localhost:8080
    ```

# III. Lưu ý khi trước khi chạy ứng dụng
1. Import CSDL `resources/data.sql`

2. Thiết lập `resources/application.properties`
   
   ```
    spring.application.name=LeDonChung_Lab_Week04

    server.port=<YOUR_PORT>
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mariadb://localhost:3306/week05
    spring.datasource.username=<YOUR_USERNAME>
    spring.datasource.password=<YOUR_PASSWORD>
    
    spring.jpa.hibernate.ddl-auto=update

   ```
   
# IV. Các chức năng
1. Test `SkillRepository`

```java
  package vn.edu.iuh.fit.donchung.skill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.donchung.entity.Skill;
import vn.edu.iuh.fit.donchung.repositories.SkillRepository;

import java.util.List;

@SpringBootTest
public class SkillTest {
    @Autowired
    private SkillRepository skillRepository;


    @Test
    void testInsert() {
        Skill skill = Skill.builder()
                .skillName("MariaDB")
                .field("IT")
                .description("Java Developer")
                .build();

        skill = skillRepository.save(skill);
        Skill actual = skillRepository.findById(skill.getId());
        assert skill.getSkillName().equals(actual.getSkillName());
    }

    @Test
    void testUpdate() {
        Skill skill = Skill.builder()
                .id(1)
                .skillName("Java")
                .field("IT")
                .description("Java Developer")
                .build();

        skill = skillRepository.save(skill);
        Skill actual = skillRepository.findById(1);
        assert skill.getSkillName().equals(actual.getSkillName());
    }

    @Test
    void testFindById() {
        Skill skill = skillRepository.findById(1);
        assert skill.getId() == 1;
    }

    @Test
    void testDelete() {
        boolean result = skillRepository.delete(1);

        Skill skill = skillRepository.findById(1);
        assert skill == null;
    }

    @Test
    void testFindAll() {
        List<Skill> skills = skillRepository.findAll();

        skills.forEach(System.out::println);
    }
}

```

2. Test `JobRepository`

```java
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


```

2. Test `CandidateSkill`

```java
package vn.edu.iuh.fit.donchung.candidate;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.entity.CandidateSkill;
import vn.edu.iuh.fit.donchung.entity.Skill;
import vn.edu.iuh.fit.donchung.repositories.CandidateRepository;
import vn.edu.iuh.fit.donchung.repositories.SkillRepository;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
public class CandidateTest {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private SkillRepository skillRepository;

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

    @Test
    void testAddSkill() {
        Candidate candidate = candidateRepository.findById(6);
        List<CandidateSkill> candidateSkills = List.of(
                CandidateSkill.builder()
                        .candidate(candidate)
                        .skill(skillRepository.findById(2))
                        .level(3)
                        .build(),
                CandidateSkill.builder()
                        .candidate(candidate)
                        .skill(skillRepository.findById(4))
                        .level(4)
                        .build(),
                CandidateSkill.builder()
                        .candidate(candidate)
                        .skill(skillRepository.findById(1))
                        .level(4)
                        .build()
        );
        boolean result = candidateRepository.addSkill(candidate, candidateSkills);
        logger.info("Add Skill: " + result);
        assert result;
    }
}
```

## 📞 Liên Hệ với tôi nếu bạn có câu hỏi nào!
<div align="left">
<a href="https://www.facebook.com/LDC01082003" target="_blank">
  <img src=https://img.shields.io/badge/facebook-%232E87FB.svg?&style=for-the-badge&logo=facebook&logoColor=white alt=facebook style="margin-bottom: 5px;" />
</a>
</div>

# Cảm ơn bạn đã xem xét dự án của tôi! 😊
