## 📝Nội Dung Dựa Án Của Tôi
- Tên dự án: LeDonChung_Lab_Week03
- Công nghệ: Servlet & JSP, JarkataEE, Làm quen với EJB
- Người hướng dẫn: Võ Văn Hải
- Người thực hiện: Lê Đôn Chủng
- Mã số sinh viên: 21023861
  
# I. Giới thiệu

Thêm giá vào một sản phẩm

# II. Cách cài đặt

Để cài đặt ứng dụng, bạn có thể làm theo các bước dưới đây:

1. Clone repository về máy của bạn:

   ```bash
   https://github.com/LeDonChung/LeDonChung_21023861_WWW.git
   ```
2. Di chuyển tới thư mục LeDonChung_Lab_Week03:

   ```bash
   cd LeDonChung_Lab_Week03
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
2. Truy cập `http://localhost:4848/login.jsf` để config pool
- Config Pool
  
![image](https://github.com/user-attachments/assets/93998eb1-c9ca-4d06-b342-8f66db641f57)


![image](https://github.com/user-attachments/assets/120a29b0-4ab8-4d98-b6c2-e82fbbd5c5e3)


- Config Resource

![image](https://github.com/user-attachments/assets/c1f81406-c1a8-464a-99dd-152874e31fa2)


4. Thiết lập `resources/META-INF/persistence.xml`
   
   ```
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
                 version="3.0">
        <persistence-unit name="LeDonChungWeek03" transaction-type="JTA">
            <jta-data-source>jdbc/week03</jta-data-source>
            <properties>
                <property name="eclipselink.ddl-generation" value="create-or-extend-tables" />
                <property name="eclipselink.ddl-generation.output-mode" value="both"/>
            </properties>
        </persistence-unit>
    </persistence>
   ```
   
# IV. Các chức năng
1. Thêm giá mới vào sản phẩm

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week03/src/main/resources/evindences/themgia.gif)


## 📞 Liên Hệ với tôi nếu bạn có câu hỏi nào!
<div align="left">
<a href="https://www.facebook.com/LDC01082003" target="_blank">
  <img src=https://img.shields.io/badge/facebook-%232E87FB.svg?&style=for-the-badge&logo=facebook&logoColor=white alt=facebook style="margin-bottom: 5px;" />
</a>
</div>

# Cảm ơn bạn đã xem xét dự án của tôi! 😊
