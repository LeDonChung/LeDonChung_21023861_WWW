## 📝Nội Dung Dựa Án Của Tôi
- **Tên dự án**: LeDonChung_Lab_Week05
- **Công nghệ**: Spring MVC, Spring Boot
- **Người hướng dẫn**: Võ Văn Hải
- **Người thực hiện**: Lê Đôn Chủng
- **Mã số sinh viên**: 21023861
- **File báo cáo**: [Báo cáo dự án][bao-cao]
- **File trình bày**: [Presentation][presentation]

[bao-cao]: https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/LeDonChung_21023861_BaoCao.docx
[presentation]: https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/LeDonChung_21023861_Presentation.pptx

# I. Giới thiệu

Bài tập yêu cầu xây dựng một hệ thống quản lý tuyển dụng với các tính năng sau:

1. **Tạo các entities** sao cho khi thực thi, các bảng dữ liệu sẽ được tạo ra theo thiết kế đã có.
2. **Viết các repository interfaces** cho các entities đã tạo.
3. **Viết các lớp service** để xử lý logic cho các hành động trong ứng dụng.
4. **Tạo các trang web** cho phép công ty đăng tin tuyển dụng và yêu cầu các kỹ năng mong muốn cho công việc.
5. **Gợi ý công việc cho ứng viên** dựa trên các kỹ năng của họ khi họ đăng nhập.
6. **Giúp các công ty tìm ứng viên** có kỹ năng phù hợp và gửi email mời.
7. **Đề xuất các kỹ năng** mà ứng viên chưa có để học thêm.
8. **Gửi mail cho ứng viên**

# II. Cách cài đặt

Để cài đặt ứng dụng, bạn có thể làm theo các bước dưới đây:

1. Clone repository về máy của bạn:

   ```bash
   https://github.com/LeDonChung/LeDonChung_21023861_WWW.git
   ```
2. Di chuyển tới thư mục LeDonChung_Lab_Week05:

   ```bash
   cd LeDonChung_Lab_Week05
   mvn install
   ```
3. Chạy ứng dụng:
   ```bash
   mvn spring-boot:run
   ```
4. Truy cập qua địa chỉ
    ```
    http://localhost:8082
    ```

# III. Lưu ý khi trước khi chạy ứng dụng
1. Import CSDL `resources/data.sql`
   
2. Thiết lập `resources/application.properties`
   
   ```
   spring.application.name=LeDonCHung_Lab_Week05
   spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
   spring.datasource.url=jdbc:mariadb://localhost:3306/works
   spring.datasource.username=root
   spring.datasource.password=${YOUR_PASSWORD}
   server.port=${YOUR_PORT}
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=update
      
   spring.mail.default-encoding=UTF-8
   spring.mail.host=smtp.gmail.com
   spring.mail.password=${YOU_MAIL_PASSWORD}
   spring.mail.port=587
   spring.mail.properties.mail.debug=true
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   spring.mail.protocol=smtp
   spring.mail.test-connection=false
   spring.mail.username=${YOUR_EMAIL}
   ```
   
# IV. Chức năng

## Account
### Candidate: 
- Username: belkis.champlin
- Password: 123456
### Company: 
- Username: angel.kutch
- Password: 123456
  
## Diagram

![](https://github.com/user-attachments/assets/3abfbef7-0ef4-44b5-8c23-afa1df8d8644)



## 1. Hệ thống đăng nhập phân quyền với Spring Security
   - COMPANY: Vai trò này dành cho các công ty, cho phép họ đăng tuyển và quản lý các vị trí công việc, được gợi ý ứng viên dựa trên yêu cầu kĩ năng công việc.
   - CANDIDATE: Vai trò này dành cho ứng viên, cho phép họ xem các công việc gợi ý dựa trên kĩ năng của ứng viên.

### `AppConfigugation.java`
```java
@Configuration
@EnableWebSecurity 
@EnableMethodSecurity // Annotation giúp các @Controller khác có thể kiểm tra Role, Authorize
public class AppConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationConfigurer() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers("/login/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .defaultSuccessUrl("/index")
                // .failureForwardUrl("/login?error") (Have custom by http)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and().httpBasic().disable();
        return http.build();
    }
}
```
### Demo Đăng nhập

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/login.gif)

### Demo Đăng ký 

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/register.gif)

## 2. Gợi ý công việc dựa trên kĩ năng của ứng viên.

### `JobService`
```java
   @Override
    public PageDto<JobDto> getJobsForCandidate(int page, int size, Long candidateId) {
        // Create page request
        PageRequest pageRequest = PageRequest.of(page, size);


        Page<Job> pageJob = jobRepository.findJobsForCandidateWithLevel(candidateId, 60, pageRequest);
        return PageDto.<JobDto>builder()
                .page(page)
                .size(size)
                .total(pageJob.getNumberOfElements())
                .totalPages(pageJob.getTotalPages())
                .values(pageJob.stream().map(jobMapper::toDto).toList())
                .build();
    }
```

### `JobRepository`
```java
   @Query("SELECT j FROM Job j " +
            "JOIN j.jobSkills js " +  // Liên kết với các kỹ năng yêu cầu của công việc
            "JOIN CandidateSkill cs ON cs.skill.id = js.skill.id " +  // Liên kết với các kỹ năng của ứng viên
            "WHERE cs.candidate.id = :candidateId " +  // Lọc theo ứng viên
            "AND cs.skillLevel >= js.skillLevel " +  // Kiểm tra mức độ kỹ năng ứng viên
            "GROUP BY j.id " +  // Nhóm theo công việc
            "HAVING COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT jss.skill.id) FROM JobSkill jss WHERE jss.job.id = j.id) >= :per " +  // So sánh tỷ lệ kỹ năng
            "ORDER BY COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT jss.skill.id) FROM JobSkill jss WHERE jss.job.id = j.id) DESC")  // Sắp xếp theo tỷ lệ
    Page<Job> findJobsForCandidateWithLevel(@Param("candidateId") Long candidateId, @Param("per") int per,
                                            Pageable pageable);
```

### Demo

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/recommend-job.gif)

## 3. Gợi ý các kĩ năng mà ứng viên còn thiếu để phù hợp với vị trí công việc

![image](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/goi-y.png)

## 4. Tìm kiếm theo nhiều tiêu chí

### `JobSpecification`

```java
   public class JobSpecification {
    public static Specification<Job> hasFilter(String filter) {
        return (root, query, criteriaBuilder) -> {
            if (filter == null || filter.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("jobName")), "%" + filter.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("compName")), "%" + filter.toLowerCase() + "%")
            );
        };
    }

    public static Specification<Job> hasAddress(String address) {
        return (root, query, criteriaBuilder) -> {
            if (address == null || address.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("address").get("city")), "%" + address.toLowerCase() + "%");
        };
    }
}
```

### Demo

![image](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/tim-kiem.png)

## 5. Chỉnh sửa thông tin cá nhân

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/update-profile.gif)


## 6. Công ty có thể tìm kiếm ứng viên phù hợp với vị trí công việc theo tỉ lệ match

### `JobService`
```java
   @Override
    public PageDto<CandidateDto> findCandidatesForJobWithLevel(Long jobId, int per, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Candidate> pageCandidates = jobRepository.findCandidatesForJobWithLevel(jobId, per, pageRequest);

        return PageDto.<CandidateDto>builder()
                .page(page)
                .size(size)
                .total(pageCandidates.getNumberOfElements())
                .totalPages(pageCandidates.getTotalPages())
                .values(pageCandidates.stream().map(candidateMapper::toDto).toList())
                .build();
    }
```

### `JobRepository`
```java
   @Query("SELECT c FROM Candidate c " +
            "JOIN c.candidateSkills cs  " +  // Liên kết kỹ năng của ứng viên với kỹ năng công việc
            "JOIN JobSkill js ON cs.skill.id = js.skill.id " +
            "WHERE js.job.id = :jobId " +  // Liên kết với công việc cụ thể
            "AND cs.skillLevel >= js.skillLevel " +  // Kiểm tra mức độ kỹ năng ứng viên đáp ứng yêu cầu
            "GROUP BY c.id " +  // Nhóm theo ứng viên
            "HAVING COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT js2.skill.id) FROM JobSkill js2 WHERE js2.job.id = :jobId) >= :per " +  // So sánh tỷ lệ kỹ năng
            "ORDER BY COUNT(DISTINCT cs.skill.id) * 100 / (SELECT COUNT(DISTINCT js2.skill.id) FROM JobSkill js2 WHERE js2.job.id = :jobId) DESC")  // Sắp xếp theo tỷ lệ
    Page<Candidate> findCandidatesForJobWithLevel(@Param("jobId") Long jobId, @Param("per") int per,
                                                  Pageable pageable);
```

### Demo

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/recommend-candidate.gif)
   

## 7. Công ty có thể đăng công việc cần tuyển.
   
![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/post-job.gif)

## 8. Công ty có thể gửi email cho ứng viên

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/send-email.gif)


## 9. Công ty có thể quản lý các công việc cần tuyển.

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week05/src/main/resources/evidences/manager-job.png)

## 📞 Liên Hệ với tôi nếu bạn có câu hỏi nào!
<div align="left">
<a href="https://www.facebook.com/LDC01082003" target="_blank">
  <img src=https://img.shields.io/badge/facebook-%232E87FB.svg?&style=for-the-badge&logo=facebook&logoColor=white alt=facebook style="margin-bottom: 5px;" />
</a>
</div>

# Cảm ơn bạn đã xem xét dự án của tôi! 😊
