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
- Mã nguồn Dockerfile, build-apps.sh, build-docker.sh : <br/>
  https://github.com/trandungchien1982/k8s/tree/03.ServiceCallService
- https://www.baeldung.com/rabbitmq
- https://www.baeldung.com/spring-amqp
- https://www.javaguides.net/2022/07/spring-boot-rabbitmq-producer-and-consumer-example.html

**Tạo ra 1 Java-Service đơn giản để handle logs tập trung giữa các services Java với nhau, 
sử dụng RabbitMQ làm nền tảng trung gian cho đơn giản.:**<br/>
- Start ở port 8700
- Spring Boot version : 2.7.16
- JDK: 8

**Các EndPoints API:**

  - GET    http://localhost:8700/logs/clear <br/>
  => Xóa toàn bộ nội dung logs lưu giữ trong bộ nhớ ... <br />
  => Thêm 1 dòng ======================= CLEAR LOGS ========================== vào cache...

  - GET    http://localhost:8700/logs/add-1-log <br/>
  => Inject 1 message mới vào RabbitMQ để giả lập 1 sự kiện ghi log ...  <br />

  - GET    http://localhost:8700/logs/add-5-log <br/>
  => Inject 10 messages mới vào RabbitMQ để giả lập 5 sự kiện ghi log liên tiếp ...  <br />

  - GET    http://localhost:8700/logs/add-50-logs <br/>
  => Inject 50 messages mới vào RabbitMQ để giả lập 50 sự kiện ghi log liên tiếp ...  <br />

  - GET    http://localhost:8700/logs/add-10K-logs <br/>
    => Inject 10K messages mới vào RabbitMQ để giả lập 10K sự kiện ghi log liên tiếp ...  <br />

**Xem Console Log khi Apps đã chạy xong**
```shell
java-logs-service_1  | 
java-logs-service_1  |   .   ____          _            __ _ _
java-logs-service_1  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
java-logs-service_1  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
java-logs-service_1  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
java-logs-service_1  |   '  |____| .__|_| |_|_| |_\__, | / / / /
java-logs-service_1  |  =========|_|==============|___/=/_/_/_/
java-logs-service_1  |  :: Spring Boot ::               (v2.7.16)
java-logs-service_1  | 
java-logs-service_1  | 15:12:46.353 INFO  - Starting LogServicesApplication using Java 1.8.0_312 on 7ad77e43d205 with PID 7 (/usr/src/myapp/log-services-rabbitmq-0.0.1-SNAPSHOT.jar started by root in /usr/src/myapp)
java-logs-service_1  | 15:12:46.356 INFO  - No active profile set, falling back to 1 default profile: "default"
java-logs-service_1  | 15:12:47.660 INFO  - Tomcat initialized with port(s): 8700 (http)
java-logs-service_1  | 15:12:47.673 INFO  - Initializing ProtocolHandler ["http-nio-8700"]
java-logs-service_1  | 15:12:47.676 INFO  - Starting service [Tomcat]
java-logs-service_1  | 15:12:47.676 INFO  - Starting Servlet engine: [Apache Tomcat/9.0.80]
java-logs-service_1  | 15:12:47.762 INFO  - Initializing Spring embedded WebApplicationContext
java-logs-service_1  | 15:12:47.762 INFO  - Root WebApplicationContext: initialization completed in 1322 ms
java-logs-service_1  | 15:12:48.691 INFO  - Starting ProtocolHandler ["http-nio-8700"]
java-logs-service_1  | 15:12:48.709 INFO  - Tomcat started on port(s): 8700 (http) with context path ''
java-logs-service_1  | 15:12:48.713 INFO  - Attempting to connect to: [rabbitmq-java-logs-service:5672]
java-logs-service_1  | 15:12:48.784 INFO  - Created new connection: rabbitConnectionFactory#50b472aa:0/SimpleConnection@60015ef5 [delegate=amqp://admin@172.20.0.13:5672/, localPort= 33296]
java-logs-service_1  | 15:12:48.867 INFO  - Started LogServicesApplication in 2.967 seconds (JVM running for 3.605)
java-logs-service_1  | 15:12:48.886 INFO  - ~> log-service, Try to send 3 sample logs :) to make sure RabbitMQ is working properly :)
java-logs-service_1  | 15:12:48.887 INFO  - ~> log-service, Sample message idx 0
java-logs-service_1  | 15:12:48.887 INFO  - ~> log-service, Sample message idx 1
java-logs-service_1  | 15:12:48.888 INFO  - ~> log-service, Sample message idx 2

```
