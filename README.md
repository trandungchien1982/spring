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

# Ví dụ [12.MVC]
==============================================================

**Tham khảo**
- https://www.javatpoint.com/spring-mvc-tutorial
- https://spring.io/guides/gs/serving-web-content/
- https://hocspringboot.net/2021/04/17/spring-boot-interceptor/

**Tìm hiểu về MVC**
- Controllers: Đảm nhận presentations
- View: Chứa các xử lý UI (HTML/CSS/JS)
- Model: Chứa các object data để xử lý map vào View Template (chẳng hạn như file HTML/JSP/FreeMarker/...)

**Ví dụ `mvc-basic`**
- Truy cập trang tự động sử dụng `RestTemplate` + `CommandLineRunner` của Spring Boot:
```shell
http://localhost:5800/greeting
------------------------------------------------------------------------------------------
10:44:59.961 INFO  - Tomcat initialized with port(s): 5800 (http)
10:44:59.970 INFO  - Initializing ProtocolHandler ["http-nio-5800"]
10:44:59.973 INFO  - Starting service [Tomcat]
10:44:59.974 INFO  - Starting Servlet engine: [Apache Tomcat/9.0.80]
10:45:00.058 INFO  - Initializing Spring embedded WebApplicationContext
10:45:00.058 INFO  - Root WebApplicationContext: initialization completed in 1926 ms
10:45:00.349 INFO  - Adding welcome page: class path resource [static/index.html]
10:45:00.527 INFO  - LiveReload server is running on port 35729
10:45:00.541 INFO  - Starting ProtocolHandler ["http-nio-5800"]
10:45:00.566 INFO  - Tomcat started on port(s): 5800 (http) with context path ''
10:45:00.579 INFO  - Started MainApplication in 2.965 seconds (JVM running for 3.59)
10:45:00.581 INFO  - Start running CLI for SpringMVC + BasicMode ...
10:45:00.693 INFO  - Initializing Spring DispatcherServlet 'dispatcherServlet'
10:45:00.694 INFO  - Initializing Servlet 'dispatcherServlet'
10:45:00.695 INFO  - Completed initialization in 1 ms
10:45:00.730 INFO  -  ---- [MVC-Basic] The GET method ... 
10:45:01.009 INFO  -  ---> Result GET: <!DOCTYPE HTML>
<html>
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p >Hello, World!</p>
</body>
</html>

10:45:01.009 INFO  -  >> We will stop the JavaApp now. Bye bye ...

```

**Ví dụ `mvc-interceptors`**
- Truy cập trang tự động sử dụng `RestTemplate` + `CommandLineRunner` của Spring Boot:
```shell
http://localhost:5810/greeting
------------------------------------------------------------------------------------------
10:37:42.845 INFO  - Tomcat initialized with port(s): 5810 (http)
10:37:42.854 INFO  - Initializing ProtocolHandler ["http-nio-5810"]
10:37:42.857 INFO  - Starting service [Tomcat]
10:37:42.857 INFO  - Starting Servlet engine: [Apache Tomcat/9.0.80]
10:37:42.969 INFO  - Initializing Spring embedded WebApplicationContext
10:37:42.969 INFO  - Root WebApplicationContext: initialization completed in 1790 ms
10:37:43.285 INFO  - Adding welcome page: class path resource [static/index.html]
10:37:43.465 INFO  - Starting ProtocolHandler ["http-nio-5810"]
10:37:43.493 INFO  - Tomcat started on port(s): 5810 (http) with context path ''
10:37:43.507 INFO  - Started MainApplication in 2.987 seconds (JVM running for 3.646)
10:37:43.510 INFO  - Start running CLI for SpringMVC + Interceptors ...
10:37:43.632 INFO  - Initializing Spring DispatcherServlet 'dispatcherServlet'
10:37:43.633 INFO  - Initializing Servlet 'dispatcherServlet'
10:37:43.634 INFO  - Completed initialization in 1 ms
10:37:43.658 INFO  -  -------- preHandle() : mvc.controllers.GreetingController#greeting(String, Model)
10:37:43.678 INFO  -  --------- The GET method: greeting() ... 
10:37:43.682 INFO  -  -------- postHandle() : mvc.controllers.GreetingController#greeting(String, Model)
10:37:44.108 INFO  -  -------- afterCompletion() : mvc.controllers.GreetingController#greeting(String, Model)

10:37:44.116 INFO  -  ---> Result GET: <!DOCTYPE HTML>
<html>
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p >Hello, World!</p>
</body>
</html>

10:37:44.117 INFO  -  >> We will stop the JavaApp now. Bye bye ...
```
