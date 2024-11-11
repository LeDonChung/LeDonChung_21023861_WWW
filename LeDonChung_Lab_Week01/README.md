## 📝Nội Dung Dựa Án Của Tôi
- Tên dự án: LeDonChung_Lab_Week01
- Công nghệ: Servlet & JSP
- Người hướng dẫn: Võ Văn Hải
- Người thực hiện: Lê Đôn Chủng
- Mã số sinh viên: 21023861
  
# I. Giới thiệu

Bài tập yêu cầu xây dựng một hệ thống quản lý tài khoản:

1. Thực hiện chức năng đăng nhập
2. User Hiển thị thông tin tài khoản user(nếu đăng nhập thành công)
3. Admin: Cho phép thêm, sửa, xóa tài khoản, cấp quyền cho một account.
4. Ghi log mỗi lần account đăng nhập, đăng xuất.
5. Một trang html hiển thị cửa sổ đăng nhập. Nếu đăng nhập thành công và là quyền admin 
thì hiển thị trang dashboard cho phép quản lý các account khác (thêm sửa xóa tài khoản). Còn không (không phải admin) thì hiển thị thông tin của người 
đăng nhập cùng các quyền mà người đó có.

# II. Cách cài đặt

Để cài đặt ứng dụng, bạn có thể làm theo các bước dưới đây:

1. Clone repository về máy của bạn:

   ```bash
   https://github.com/LeDonChung/LeDonChung_21023861_WWW.git
   ```
2. Di chuyển tới thư mục LeDonChung_Lab_Week01:

   ```bash
   cd LeDonChung_Lab_Week01
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
   
3. Thiết lập `resources/META-INF/persistence.xml`
   
   ```
    <?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <persistence-unit name="LeDonChung MariaDB">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <!-- Thay đổi port và tên database-->
            <property name="jakarta.persistence.jdbc.url"
                      value="jdbc:mariadb://localhost:3306/Week01" />
            <property
                    name="jakarta.persistence.jdbc.user" value="<YOUR_USER>" />
            <property
                    name="jakarta.persistence.jdbc.password"
                    value="<YOUR_PASSWORD>" />
            <property
                    name="jakarta.persistence.jdbc.driver"
                    value="org.mariadb.jdbc.Driver" />

            <property
                    name="jakarta.persistence.jdbc.dialect"
                    value="org.hibernate.dialect.MariaDBDialect" />

            <property
                    name="hibernate.show_sql" value="true" />
            <property
                    name="hibernate.format_sql" value="true" />
            <property
                    name="hibernate.hbm2ddl.auto" value="update" />
        </properties>
    </persistence-unit>

</persistence>
   ```
   
# IV. Các chức năng
## 1. Đăng nhập với tài khoản và mật khẩu

#### 1. Đăng nhập với quyền Admin 
![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week01/src/main/resources/evidences/login-admin.gif)

#### 2. Đăng nhập với quyền User(Other) 
![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week01/src/main/resources/evidences/login-user.gif)

## 2. Thêm tài khoản mới với quyền Admin

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week01/src/main/resources/evidences/add-new.gif)

## 3. Cập nhật và thêm quyền cho một tài khoản với quyền Admin

![Demo](https://github.com/LeDonChung/LeDonChung_21023861_WWW/blob/main/LeDonChung_Lab_Week01/src/main/resources/evidences/update.gif)

## 4. Ghi lại log của từng user đăng nhập 

![Demo](![image](https://github.com/user-attachments/assets/dd45c6fb-d90b-4474-8bff-6a46e5f192a3)


## 📞 Liên Hệ với tôi nếu bạn có câu hỏi nào!
<div align="left">
<a href="https://www.facebook.com/LDC01082003" target="_blank">
  <img src=https://img.shields.io/badge/facebook-%232E87FB.svg?&style=for-the-badge&logo=facebook&logoColor=white alt=facebook style="margin-bottom: 5px;" />
</a>
</div>

# Cảm ơn bạn đã xem xét dự án của tôi! 😊
