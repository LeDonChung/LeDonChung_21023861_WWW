# I. Giới thiệu

Bài tập yêu cầu xây dựng một hệ thống quản lý tuyển dụng với các tính năng sau:

1. **Tạo các entities** sao cho khi thực thi, các bảng dữ liệu sẽ được tạo ra theo thiết kế đã có.
2. **Viết các repository interfaces** cho các entities đã tạo.
3. **Viết các lớp service** để xử lý logic cho các hành động trong ứng dụng.
4. **Tạo các trang web** cho phép công ty đăng tin tuyển dụng và yêu cầu các kỹ năng mong muốn cho công việc.
5. **Gợi ý công việc cho ứng viên** dựa trên các kỹ năng của họ khi họ đăng nhập.
6. **Giúp các công ty tìm ứng viên** có kỹ năng phù hợp và gửi email mời.
7. **Đề xuất các kỹ năng** mà ứng viên chưa có để học thêm.

# II. Cách cài đặt

Để cài đặt ứng dụng, bạn có thể làm theo các bước dưới đây:

1. Clone repository về máy của bạn:

   ```bash
   https://github.com/LeDonChung/LeDonChung_21023861_WWW.git
   ```
2. Di chuyển tới thư mục LeDonChung_Lab_Week07:

   ```bash
   cd LeDonChung_Lab_Week07
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
1. Import CSDL
   
    ```
    resources/data.sql
   ```
2. Thiết lập resources/application.properties
   
   ```
    spring.application.name=LeDonCHung_Lab_Week07
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mariadb://localhost:3306/works
    spring.datasource.username=<your_user>
    spring.datasource.password=<your_password>
    server.port=8082
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
   ```
   
# IV. Chức năng đặc biệt
## 1. Hệ thống đăng nhập phân quyền với Spring Security
   - COMPANY: Vai trò này dành cho các công ty, cho phép họ đăng tuyển và quản lý các vị trí công việc, được gợi ý ứng viên dựa trên yêu cầu kĩ năng công việc.
   - CANDIDATE: Vai trò này dành cho ứng viên, cho phép họ xem các công việc gợi ý dựa trên kĩ năng của ứng viên.
     
![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week07/src/main/resources/evidences/login.gif)

## 2. Gợi ý công việc dựa trên kĩ năng của ứng viên.

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week07/src/main/resources/evidences/recommend-job.gif)

## 3. Gợi ý các kĩ năng mà ứng viên còn thiếu để phù hợp với vị trí công việc
   
![image](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week07/src/main/resources/evidences/goiy.jpg)

## 4. Công ty có thể tìm kiếm ứng viên phù hợp với vị trí công việc theo tỉ lệ match
   
![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week07/src/main/resources/evidences/active.gif)
   

## 5. Công ty có thể đăng công việc cần tuyển.
   
![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week07/src/main/resources/evidences/new-job.gif)

## 6. Công ty có thể quản lý các công việc cần tuyển.

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week07/src/main/resources/evidences/update-job.gif)

## 📞 Liên Hệ với tôi nếu bạn có câu hỏi nào!
<div align="left">
<a href="https://www.facebook.com/LDC01082003" target="_blank">
  <img src=https://img.shields.io/badge/facebook-%232E87FB.svg?&style=for-the-badge&logo=facebook&logoColor=white alt=facebook style="margin-bottom: 5px;" />
</a>
</div>

# Cảm ơn bạn đã xem xét dự án của tôi! 😊
