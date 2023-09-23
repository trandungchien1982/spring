# spring
Các ví dụ liên quan đến Spring/SpringBoot từ cơ bản đến nâng cao<br/>
Mỗi nhánh trong Repo sẽ là 1 ví dụ/ giải pháp/ project mẫu trong Spring

# Môi trường phát triển
- Spring Boot 2.7.16
- JDK 11

# Build Tools sử dụng
- Maven + Gradle
- Intelij IDEA

# Folder liên quan trên Windows
```
D:\Projects\spring
```

==============================================================

# Ví dụ [03.RestfulAPI]
==============================================================

**Ta sẽ tạo 1 App Spring Boot có apply Restful API, bao gồm:**<br/>
  GET, POST, PUT, DELETE ứng với mô hình CRUD<br/>

- Start ở port 8100
- Spring Boot version : 2.7.16
- JDK: 11

**Tạo RESTful API với các endpoints sau đây:**
  - GET    http://localhost:8100/api/get-users
  - GET    http://localhost:8100/api/get-users?limit=3
  - GET    http://localhost:8100/api/get-user-data/12 <br/>

  - POST   http://localhost:8100/api/add-user
  ```shell
    curl --location --request POST 'http://localhost:8100/api/add-user'
  ```
  - POST   http://localhost:8100/api/add-custom-user <br/>
  ```shell
    curl --location --request POST 'http://localhost:8100/api/add-custom-user' \
      --header 'Content-Type: application/json' \
      --data-raw '{
          "userId": 100,
          "name": "User 100",
          "age": 120
      }
    '
  ```

  - PUT    http://localhost:8100/api/update-user/3 <br/>
  ```shell
    curl --location --request PUT 'http://localhost:8100/api/update-user/3' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "User Name update of 3",
        "age": 3100
    }
    '
  ```

  - DELETE http://localhost:8100/api/delete-user/5 <br/>
  ```shell
      curl --location --request DELETE 'http://localhost:8100/api/delete-user/5'
  ```

**Tạo Table DB giả lập trong H2 database**
  - Tạo Entity/Repository mẫu để tương tác với H2 (memory database)
  - Tạo Service/Controller

