package vn.edu.fit.student.donchung.test;

import com.neovisionaries.i18n.CountryCode;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.backend.dtos.JobDto;
import vn.edu.fit.student.donchung.backend.dtos.PageDto;
import vn.edu.fit.student.donchung.backend.entities.*;
import vn.edu.fit.student.donchung.backend.enums.SkillLevel;
import vn.edu.fit.student.donchung.backend.enums.SkillType;
import vn.edu.fit.student.donchung.backend.repositories.*;
import vn.edu.fit.student.donchung.backend.services.JobService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootTest
public class RandomDataTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;
    @Test
    public void insertRole() {
        Role role1 = Role
                .builder()
                .name("Company")
                .code("COMPANY")
                .build();

        Role role2 = Role
                .builder()
                .name("Admin")
                .code("ADMIN")
                .build();

        Role role3 = Role
                .builder()
                .name("Candidate")
                .code("CANDIDATE")
                .build();

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);

    }

    @Test
    public void randomITSkills() {
        Faker faker = new Faker(new Locale("en"));

        // List of IT-related skills
        String[] itSkills = {
                "Java", "Python", "JavaScript", "SQL", "C#", "C++", "Go", "Ruby", "PHP",
                "Swift", "Kotlin", "HTML", "CSS", "React", "Angular", "Vue.js", "Node.js",
                "Spring Framework", "Django", "Flask", "Laravel", "Docker", "Kubernetes",
                "AWS", "Azure", "Google Cloud", "Machine Learning", "Data Science",
                "Cybersecurity", "Penetration Testing", "DevOps", "CI/CD", "Microservices",
                "REST APIs", "GraphQL", "Blockchain", "Artificial Intelligence", "Big Data",
                "NoSQL", "MongoDB", "PostgreSQL", "MySQL", "Redis", "Git", "Linux", "Networking"
        };

        // Set to ensure no duplicate skill names
        Set<String> uniqueSkills = new HashSet<>();
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            // Randomly select IT skills from the array
            String skillName = itSkills[rand.nextInt(itSkills.length)];

            // Ensure the skill is unique
            while (!uniqueSkills.add(skillName)) {
                skillName = itSkills[rand.nextInt(itSkills.length)];
            }

            // Create and save the skill entity
            Skill skill = Skill.builder()
                    .skillDescription(faker.lorem().sentence())
                    .skillName(skillName)
                    .type(SkillType.values()[rand.nextInt(SkillType.values().length)]) // Random skill type
                    .build();

            skillRepository.save(skill);
        }
    }

    @Test
    public void randomCompany() {

        Optional<Role> role = roleRepository.findByCode("COMPANY");
        if(role.isEmpty()) {
            return;
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role.get());
        Faker faker = new Faker(new Locale("en"));
        for (int i = 1; i <= 50; i++) {
            Company c = Company.builder()
                    .roles(roles)
                    .about(faker.company().bs())
                    .phone(faker.phoneNumber().phoneNumber())
                    .email(faker.siliconValley().email())
                    .address(Address.builder()
                            .city(faker.address().city())
                            .country(CountryCode.valueOf(faker.address().countryCode()))
                            .zipcode(faker.address().zipCode())
                            .street(faker.address().streetAddress())
                            .number(faker.address().streetAddressNumber())
                            .build())
                    .webUrl(faker.internet().url())
                    .compName(faker.company().name())
                    .username(faker.internet().username())
                    .password(passwordEncoder.encode("123456"))
                    .build();


            companyRepository.saveAndFlush(c);
        }
    }



    @Autowired
    private JobSkillRepository jobSkillRepository;


    @Test
    public void randomJobForCompany() {
        Faker faker = new Faker(new Locale("en"));
        List<Company> companies = companyRepository.findAll();
        List<Skill> skills = skillRepository.findAll();

        for (Company company : companies) {
            for (int i = 1; i <= 10; i++) {
                // Create a job
                Job job = Job.builder()
                        .jobName(faker.job().title())
                        .jobDesc(faker.coffee().descriptor())
                        .company(company)
                        .build();
                job = jobRepository.saveAndFlush(job);

                // Randomly choose number of skills (between 3 and 7)
                int skillCount = new Random().nextInt(5) + 3; // Random number between 3 and 7

                List<JobSkill> jobSkills = new ArrayList<>();
                Set<Long> selectedSkillIds = new HashSet<>(); // Set to store unique skill IDs

                while (selectedSkillIds.size() < skillCount) {
                    Skill skill = skills.get(new Random().nextInt(skills.size()));
                    if (!selectedSkillIds.contains(skill.getId())) { // Ensure skill is unique
                        selectedSkillIds.add(skill.getId());

                        JobSkill jobSkill = JobSkill.builder()
                                .id(JobSkillId.builder()
                                        .jobId(job.getId())  // Now jobId is available
                                        .skillId(skill.getId())
                                        .build())
                                .job(job)
                                .skill(skill)
                                .skillLevel(SkillLevel.values()[new Random().nextInt(SkillLevel.values().length)])
                                .moreInfos(faker.job().position())
                                .build();

                        jobSkills.add(jobSkill);
                    }
                }

                jobSkillRepository.saveAllAndFlush(jobSkills);
            }
        }
    }


    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public static LocalDate randomDateBetween(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        Random random = new Random();
        long randomDays = random.nextLong(daysBetween + 1); // +1 to include the end date
        return startDate.plusDays(randomDays);
    }

    @Test
    public void randomCandidate() {
        Faker faker = new Faker(new Locale("en"));
        Optional<Role> role = roleRepository.findByCode("CANDIDATE");
        if (role.isEmpty()) {
            return;
        }

        List<Skill> skills = skillRepository.findAll();
        List<Role> roles = new ArrayList<>();
        roles.add(role.get());

        for (int i = 1; i <= 50; i++) {

            Candidate candidate = Candidate.builder()
                    .roles(roles)
                    .phone(faker.phoneNumber().phoneNumber())
                    .dob(randomDateBetween(LocalDate.of(1990, 1, 1), LocalDate.now()))
                    .email(faker.siliconValley().email())
                    .address(Address.builder()
                            .city(faker.address().city())
                            .country(CountryCode.valueOf(faker.address().countryCode()))
                            .zipcode(faker.address().zipCode())
                            .street(faker.address().streetAddress())
                            .number(faker.address().streetAddressNumber())
                            .build())
                    .fullName(faker.name().fullName())
                    .username(faker.internet().username())
                    .password(passwordEncoder.encode("123456"))
                    .candidateSkills(new ArrayList<>())
                    .build();

            List<Experience> experiences = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Experience experience = Experience.builder()
                        .role(faker.job().position())
                        .fromDate(randomDateBetween(LocalDate.of(1990, 1, 1), LocalDate.now()))
                        .toDate(randomDateBetween(LocalDate.of(1990, 1, 1), LocalDate.now()))
                        .companyName(faker.company().name())
                        .candidate(candidate)
                        .workDescription(faker.job().position())
                        .build();
                experiences.add(experience);
            }

            candidate.setExperiences(experiences);
            candidate = candidateRepository.saveAndFlush(candidate);

            // Randomly choose number of skills (from 3 to 7)
            int numberOfSkills = 3 + new Random().nextInt(5); // Random number between 3 and 7

            // Create a set to avoid duplicate skills
            Set<Skill> chosenSkills = new HashSet<>();
            while (chosenSkills.size() < numberOfSkills) {
                Skill skill = skills.get(new Random().nextInt(skills.size()));
                chosenSkills.add(skill); // Adds only unique skills
            }

            List<CandidateSkill> candidateSkills = new ArrayList<>();
            for (Skill skill : chosenSkills) {
                CandidateSkill candidateSkill = CandidateSkill.builder()
                        .id(CandidateSkillId.builder()
                                .canId(candidate.getId())
                                .skillId(skill.getId())
                                .build())
                        .candidate(candidate)
                        .skill(skill)
                        .skillLevel(SkillLevel.values()[new Random().nextInt(SkillLevel.values().length)])
                        .moreInfos(faker.job().position())
                        .build();
                candidateSkills.add(candidateSkill);
            }

            candidateSkillRepository.saveAllAndFlush(candidateSkills);
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void demoTest() {
        ResponseEntity<List<Company>> response = restTemplate.exchange(
                "http://localhost:8082/api/companies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Company>>() {});

        List<Company> companies = response.getBody();

        assert companies != null;
        companies.forEach(System.out::println);

    }

    @Test
    public void testFindJobsForCandidateWithLevel() {
        PageDto<JobDto> jobs = jobService.getJobsForCandidate(0, 1, 51L);

        System.out.println(jobs);
    }

    @Autowired
    private JobService jobService;
    @Test
    public void testFindJobsForCandidateWithLevel2() {
        PageDto<JobDto> jobs = jobService.getJobsForCandidate(1, 1, 51L);
    }
}
