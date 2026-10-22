# spring
Các ví dụ liên quan đến Spring/SpringBoot từ cơ bản đến nâng cao<br/>
Mỗi nhánh trong Repo sẽ là 1 ví dụ/ giải pháp/ project mẫu trong Spring

# Môi trường phát triển
- Spring Framework 2.x hoặc 3.x
- JDK 8/ 11 hoặc 17

# Build Tools sử dụng
- Maven + Gradle
- Intelij IDEA

# Folder liên quan trên Windows
```
D:\Projects\spring
```

# Tạo demo về gửi message qua RabbitMQ, Producer -> RabbitMQ -> Consumer bao gồm 2 trường hợp :
- GET /ok-message -> OkMessageConsumer: Gửi message bình thường
- GET /failed-message -> SimulateFailedConsumer:
  - Khi bật ff `ff-simulate-error` thì sẽ raised exception để giả lập error processing
  - Ghi log bao gồm key, message, retryCount / Max Retry
