## 📝Nội Dung Dựa Án Của Tôi
- Tên dự án: LeDonChung_Lab_Week02
- Công nghệ: Servlet & JSP, JarkataEE
- Người hướng dẫn: Võ Văn Hải
- Người thực hiện: Lê Đôn Chủng
- Mã số sinh viên: 21023861
  
# I. Giới thiệu

Bài tập yêu cầu xây dựng một hệ thống đặt hàng:
Backend:
- Lập REST API cho các thao tác cần thiết của yêu cầu này (dùng JakartaEE):
- Các thao tác CRUD cho các đối tượng, lập order. (dùng JPA).
- Thống kê order theo ngày, theo khoảng thời gian.
- Thống kê order theo nhân viên bán hàng trong 1 khoảng thời gian
Frontend:
- Tạo các trang web cho việc hiển thị sản phẩm, chọn vào giỏ hàng và thanh toán (giả lập 
việc thanh toán qua thẻ nếu có thể)
- Vẽ biểu đồ giá theo thời gian

# II. Cách cài đặt

Để cài đặt ứng dụng, bạn có thể làm theo các bước dưới đây:

1. Clone repository về máy của bạn:

   ```bash
   https://github.com/LeDonChung/LeDonChung_21023861_WWW.git
   ```
2. Di chuyển tới thư mục LeDonChung_Lab_Week02:

   ```bash
   cd LeDonChung_Lab_Week02
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
  
![image](https://github.com/user-attachments/assets/a91646c3-5ad3-4a2c-ad64-acc48732f15a)

![image](https://github.com/user-attachments/assets/7b58c610-1c86-488c-9709-ea9f0561aa6e)

- Config Resource

![image](https://github.com/user-attachments/assets/08076cac-5ad4-45c6-b99c-096df38dec08)


4. Thiết lập `resources/META-INF/persistence.xml`
   
   ```
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
                 version="3.0">
        <persistence-unit name="LeDonChungWeek02" transaction-type="JTA">
            <jta-data-source>jdbc/week02</jta-data-source>
            <properties>
                <property name="eclipselink.ddl-generation" value="create-or-extend-tables" />
                <property name="eclipselink.ddl-generation.output-mode" value="both"/>
            </properties>
        </persistence-unit>
    </persistence>
   ```
   
# IV. Các chức năng
## 1. Đăng nhập

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week02/src/main/resources/evidences/login.gif)

## 2. Tạo hóa đơn

### Khách hàng mới

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week02/src/main/resources/evidences/order-customer-new.gif)

### Khách hàng cũ

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week02/src/main/resources/evidences/order-customer-old.gif)



## 📞 Liên Hệ với tôi nếu bạn có câu hỏi nào!
<div align="left">
<a href="https://www.facebook.com/LDC01082003" target="_blank">
  <img src=https://img.shields.io/badge/facebook-%232E87FB.svg?&style=for-the-badge&logo=facebook&logoColor=white alt=facebook style="margin-bottom: 5px;" />
</a>
</div>

# Cảm ơn bạn đã xem xét dự án của tôi! 😊
