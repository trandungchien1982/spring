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

# Ví dụ [09.LogServices+RabbitMQ]
==============================================================

**Tham khảo**
- https://www.baeldung.com/rabbitmq
- https://www.baeldung.com/spring-amqp
- https://www.javaguides.net/2022/07/spring-boot-rabbitmq-producer-and-consumer-example.html

**Tạo ra 1 Java-Service đơn giản để handle logs tập trung giữa các services Java với nhau, 
sử dụng RabbitMQ làm nền tảng trung gian cho đơn giản.:**<br/>
- Start ở port 8500
- Spring Boot version : 2.7.16
- JDK: 11

**Các EndPoints API:**
  - GET    http://localhost:8500/logs/all <br />
  => Trang chính liệt kê toàn bộ logs kể từ khi ứng dụng Start
  
  - GET    http://localhost:8500/logs/add-gaps <br />
  => Thêm 3 dòng ================================================ để tạo Gaps và xem logs dễ hơn ... <br /> 

  - GET    http://localhost:8100/logs/clear <br/>
  => Xóa toàn bộ nội dung logs lưu giữ trong bộ nhớ ... <br />
  => Thêm 1 dòng ======================= CLEAR LOGS ========================== vào cache...

  - GET    http://localhost:8100/logs/add-1-log <br/>
  => Inject 1 message mới vào RabbitMQ để giả lập 1 sự kiện ghi log ...  <br />

  - GET    http://localhost:8100/logs/add-5-log <br/>
  => Inject 10 messages mới vào RabbitMQ để giả lập 5 sự kiện ghi log liên tiếp ...  <br />

  - GET    http://localhost:8100/logs/add-50-log <br/>
    => Inject 100 messages mới vào RabbitMQ để giả lập 100 sự kiện ghi log liên tiếp ...  <br />

**Xem Console Log khi Apps đã chạy xong**
```shell

```
