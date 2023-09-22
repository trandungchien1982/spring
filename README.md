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

# Ví dụ [02.RunAppOnCLI]
==============================================================

**Tiến hành đóng gói Spring Boot App trở thành 1 JavaApp hoàn chỉnh:**<br/>
```shell
  ./gradlew build
  ------------------------------
  > Task :test
    12:42:05.785 [SpringApplicationShutdownHook] INFO  - Closing JPA EntityManagerFactory for persistence unit 'default'
    12:42:05.785 [SpringApplicationShutdownHook] INFO  - HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
    12:42:05.790 [SpringApplicationShutdownHook] INFO  - HikariPool-1 - Shutdown initiated...
    12:42:05.793 [SpringApplicationShutdownHook] INFO  - HikariPool-1 - Shutdown completed
    
    Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.
    
    You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.
    
    For more on this, please refer to https://docs.gradle.org/8.2.1/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.
    
    BUILD SUCCESSFUL in 11s
```
**File JAR sẽ được tạo ra**
```shell
  build/libs/run-app-cli-0.0.1-SNAPSHOT.jar
```

**Chạy JavaApp trên môi trường JDK 11 trở lên**
```shell
  java -jar build/libs/run-app-cli-0.0.1-SNAPSHOT.jar
  ------------------------------------------------------------
        .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::               (v2.7.16)
    
    12:46:05.424 [main] INFO  - Starting SpringBootApplication using Java 17.0.8 on MacBook-Air-cua-Doan.local with PID 66793 (/Users/tdc/spring/run-app-cli/build/libs/run-app-cli-0.0.1-SNAPSHOT.jar started by tdc in /Users/tdc/spring/run-app-cli)
    12:46:05.429 [main] INFO  - No active profile set, falling back to 1 default profile: "default"
    12:46:06.131 [main] INFO  - Bootstrapping Spring Data JPA repositories in DEFAULT mode.
    12:46:06.207 [main] INFO  - Finished Spring Data repository scanning in 63 ms. Found 1 JPA repository interfaces.
    12:46:06.828 [main] INFO  - HikariPool-1 - Starting...
    12:46:07.136 [main] INFO  - HikariPool-1 - Start completed.
    12:46:07.215 [main] INFO  - HHH000204: Processing PersistenceUnitInfo [name: default]
    12:46:07.309 [main] INFO  - HHH000412: Hibernate ORM core version 5.6.15.Final
    12:46:07.599 [main] INFO  - HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
    12:46:07.795 [main] INFO  - HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
    12:46:08.610 [main] INFO  - HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
    12:46:08.625 [main] INFO  - Initialized JPA EntityManagerFactory for persistence unit 'default'
    12:46:09.109 [main] INFO  - Started SpringBootApplication in 4.243 seconds (JVM running for 5.123)
    12:46:09.111 [main] INFO  - Start running CLI for JavaApp ...
    12:46:09.391 [main] WARN  - Total users: 3
    12:46:09.392 [main] INFO  -  -- User: User(userId=1695361569111, name=Name:1695361569111, age=11)
    12:46:09.392 [main] INFO  -  -- User: User(userId=1695361569228, name=Name:1695361569228, age=28)
    12:46:09.392 [main] INFO  -  -- User: User(userId=1695361569230, name=Name:1695361569230, age=30)
    12:46:09.392 [main] INFO  -  >> We will stop the JavaApp now. Bye bye ...
    12:46:09.397 [SpringApplicationShutdownHook] INFO  - Closing JPA EntityManagerFactory for persistence unit 'default'
    12:46:09.398 [SpringApplicationShutdownHook] INFO  - HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
    12:46:09.402 [SpringApplicationShutdownHook] INFO  - HikariPool-1 - Shutdown initiated...
    12:46:09.405 [SpringApplicationShutdownHook] INFO  - HikariPool-1 - Shutdown completed.

```
