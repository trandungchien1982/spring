# spring
Các ví dụ liên quan đến Spring/SpringBoot từ cơ bản đến nâng cao<br/>
Mỗi nhánh trong Repo sẽ là 1 ví dụ/ giải pháp/ project mẫu trong Spring

# Môi trường phát triển
- Spring Framework 2.7.16
- JDK 11

# Build Tools sử dụng
- Maven + Gradle
- Intelij IDEA

# Folder liên quan trên Windows
```
D:\Projects\spring
```

==============================================================

# Ví dụ [01.HelloWorld]
==============================================================

**Ta sẽ tạo 1 App Spring Boot mẫu như sau:**<br/>
(Lấy từ Spring Initializr)
- Start ở port 8100
- Spring Boot version : 2.7.16
- JDK: 11
- Các dependencies hay dùng :
  - Lombok
  - Web/MVC
  - Thymleaf
  - JPA/Hibernate
  - H2 Database
- Tạo RESTful API với các endpoints sau đây:
  - GET /api/get-users
  - POST /api/add-user
- Tạo Table DB giả lập trong H2 database
  - Tạo Entity/Repository mẫu để tương tác với H2 (memory database)
  - Tạo Service/Controller theo mô hình MVC để tạo trang /welcome, sử dụng Thymleaf