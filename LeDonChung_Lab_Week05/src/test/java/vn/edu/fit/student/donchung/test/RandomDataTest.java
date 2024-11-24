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
        Faker faker = new Faker(new Locale("vi"));

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
        Faker faker = new Faker(new Locale("vi"));
        Faker faker2 = new Faker(new Locale("en"));

        for (int i = 1; i <= 50; i++) {
            Company c = Company.builder()
                    .roles(roles)
                    .about(faker.lorem().sentence())
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
                    .username(faker2.internet().username())
                    .password(passwordEncoder.encode("123456"))
                    .build();


            companyRepository.saveAndFlush(c);
        }
    }



    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Test
    public void randomJobForCompanyVI() {
        Faker faker = new Faker(new Locale("vi"));
        List<Company> companies = companyRepository.findAll();
        List<Skill> skills = skillRepository.findAll();

        for (Company company : companies) {
            for (int i = 1; i <= 10; i++) {
                // Randomly choose number of skills (between 3 and 7)
                int skillCount = new Random().nextInt(5) + 3; // Random number between 3 and 7

                List<Skill> selectedSkills = new ArrayList<>();
                Set<Long> selectedSkillIds = new HashSet<>(); // Set to store unique skill IDs

                while (selectedSkillIds.size() < skillCount) {
                    Skill skill = skills.get(new Random().nextInt(skills.size()));
                    if (!selectedSkillIds.contains(skill.getId())) { // Ensure skill is unique
                        selectedSkillIds.add(skill.getId());
                        selectedSkills.add(skill);
                    }
                }

                // Generate job description based on selected skills
                StringBuilder jobDescBuilder = new StringBuilder();
                jobDescBuilder.append("<p><strong>Công việc yêu cầu kỹ năng: ");
                for (Skill skill : selectedSkills) {
                    jobDescBuilder.append(skill.getSkillName()).append(", ");
                }
                jobDescBuilder.delete(jobDescBuilder.length() - 2, jobDescBuilder.length()); // Remove trailing comma and space
                jobDescBuilder.append(".</strong></p>\n");

                // Add detailed job responsibilities based on selected skills
                jobDescBuilder.append("<p>Chi tiết công việc bao gồm:</p>\n");
                for (Skill skill : selectedSkills) {
                    jobDescBuilder.append("<p>- ");
                    switch (skill.getSkillName()) {
                        case "HTML":
                        case "CSS":
                            jobDescBuilder.append("Phát triển và định dạng các giao diện web với ")
                                    .append(skill.getSkillName())
                                    .append(" để đảm bảo tính trực quan và đáp ứng.");
                            break;
                        case "JavaScript":
                            jobDescBuilder.append("Tạo và tối ưu hóa chức năng động cho trang web bằng ")
                                    .append(skill.getSkillName())
                                    .append(".");
                            break;
                        case "React":
                        case "Angular":
                        case "Vue.js":
                            jobDescBuilder.append("Xây dựng và cải tiến giao diện người dùng với ")
                                    .append(skill.getSkillName())
                                    .append(", đảm bảo hiệu suất cao và trải nghiệm người dùng tốt nhất.");
                            break;
                        case "Node.js":
                            jobDescBuilder.append("Phát triển backend sử dụng Node.js để xử lý logic nghiệp vụ và kết nối với cơ sở dữ liệu.");
                            break;
                        case "Spring Framework":
                        case "Django":
                        case "Flask":
                        case "Laravel":
                            jobDescBuilder.append("Xây dựng và bảo trì các ứng dụng web với framework ")
                                    .append(skill.getSkillName())
                                    .append(" nhằm đảm bảo tính bảo mật và khả năng mở rộng của hệ thống.");
                            break;
                        case "Git":
                            jobDescBuilder.append("Quản lý phiên bản mã nguồn và làm việc nhóm hiệu quả bằng Git.");
                            break;
                        case "Docker":
                        case "Kubernetes":
                            jobDescBuilder.append("Sử dụng ")
                                    .append(skill.getSkillName())
                                    .append(" để tạo và quản lý container, đảm bảo việc triển khai ứng dụng nhất quán và đáng tin cậy.");
                            break;
                        case "AWS":
                        case "Azure":
                        case "Google Cloud":
                            jobDescBuilder.append("Triển khai và quản lý các thành phần ứng dụng trên nền tảng đám mây của ")
                                    .append(skill.getSkillName())
                                    .append(".");
                            break;
                        case "Machine Learning":
                        case "Data Science":
                            jobDescBuilder.append("Phân tích dữ liệu và phát triển các mô hình ")
                                    .append(skill.getSkillName())
                                    .append(" để đưa ra dự đoán và tối ưu hóa quyết định.");
                            break;
                        case "Cybersecurity":
                        case "Penetration Testing":
                            jobDescBuilder.append("Đảm bảo an ninh cho hệ thống bằng cách thực hiện các biện pháp ")
                                    .append(skill.getSkillName())
                                    .append(".");
                            break;
                        case "DevOps":
                        case "CI/CD":
                            jobDescBuilder.append("Thiết lập quy trình ")
                                    .append(skill.getSkillName())
                                    .append(" để tự động hóa việc triển khai và nâng cao hiệu suất phát triển phần mềm.");
                            break;
                        case "Microservices":
                            jobDescBuilder.append("Thiết kế và triển khai hệ thống ứng dụng bằng kiến trúc Microservices để tối ưu khả năng mở rộng.");
                            break;
                        case "REST APIs":
                        case "GraphQL":
                            jobDescBuilder.append("Tích hợp và phát triển API ")
                                    .append(skill.getSkillName())
                                    .append(" để kết nối các dịch vụ và luồng dữ liệu trong hệ thống.");
                            break;
                        case "Blockchain":
                        case "Artificial Intelligence":
                            jobDescBuilder.append("Xây dựng ứng dụng sử dụng công nghệ ")
                                    .append(skill.getSkillName())
                                    .append(" để cải thiện tính bảo mật và tự động hóa thông minh.");
                            break;
                        case "Big Data":
                        case "NoSQL":
                        case "MongoDB":
                        case "PostgreSQL":
                        case "MySQL":
                            jobDescBuilder.append("Quản lý và phân tích dữ liệu lớn với công cụ cơ sở dữ liệu như ")
                                    .append(skill.getSkillName())
                                    .append(" để đưa ra quyết định kinh doanh hiệu quả.");
                            break;
                        case "Redis":
                            jobDescBuilder.append("Triển khai bộ nhớ tạm thời bằng Redis để cải thiện hiệu suất và giảm thời gian truy xuất dữ liệu.");
                            break;
                        case "Linux":
                            jobDescBuilder.append("Quản lý và cấu hình hệ thống sử dụng Linux, đảm bảo tính ổn định và bảo mật cho môi trường máy chủ.");
                            break;
                        case "Networking":
                            jobDescBuilder.append("Quản lý và bảo trì các kết nối mạng, đảm bảo tính thông suốt và an toàn.");
                            break;
                        default:
                            jobDescBuilder.append("Sử dụng kỹ năng ")
                                    .append(skill.getSkillName())
                                    .append(" để đóng góp vào dự án và giải quyết các vấn đề phát sinh.");
                            break;
                    }
                    jobDescBuilder.append("</p>\n");
                }

                // Create a job
                Job job = Job.builder()
                        .jobName(faker.job().title())
                        .jobDesc(jobDescBuilder.toString())
                        .company(company)
                        .build();
                job = jobRepository.saveAndFlush(job);

                List<JobSkill> jobSkills = new ArrayList<>();

                for (Skill skill : selectedSkills) {
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

                jobSkillRepository.saveAllAndFlush(jobSkills);
            }
        }
    }


    @Test
    public void randomJobForCompany() {
        Faker faker = new Faker(new Locale("vi"));
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
        Faker faker = new Faker(new Locale("vi"));
        Faker faker2 = new Faker(new Locale("en"));

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
                    .username(faker2.internet().username())
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
