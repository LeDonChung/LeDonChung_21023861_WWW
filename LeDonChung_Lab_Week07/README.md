# Giới thiệu

Bài tập yêu cầu xây dựng một hệ thống quản lý tuyển dụng với các tính năng sau:

1. **Tạo các entities** sao cho khi thực thi, các bảng dữ liệu sẽ được tạo ra theo thiết kế đã có.
2. **Viết các repository interfaces** cho các entities đã tạo.
3. **Viết các lớp service** để xử lý logic cho các hành động trong ứng dụng.
4. **Tạo các trang web** cho phép công ty đăng tin tuyển dụng và yêu cầu các kỹ năng mong muốn cho công việc.
5. **Gợi ý công việc cho ứng viên** dựa trên các kỹ năng của họ khi họ đăng nhập.
6. **Giúp các công ty tìm ứng viên** có kỹ năng phù hợp và gửi email mời.
7. **Đề xuất các kỹ năng** mà ứng viên chưa có để học thêm.

# Cách cài đặt

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

# Lưu ý khi trước khi chạy ứng dụng
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
   
