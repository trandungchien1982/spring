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

# Ví dụ [07.JPA+2DBs+MySQL+PostgreSQL]
==============================================================

**Tham khảo**
- https://github.com/yandex-qatools/postgresql-embedded
- https://stackoverflow.com/questions/14314026/embedded-postgresql-for-java-junit-tests
- https://www.baeldung.com/spring-boot-configure-data-source-programmatic
- ..................

**Ta sẽ tạo 1 App Spring Boot sử dụng JPA và kết nối đến 2 DBs cùng lúc: MySQL + PostgreSQL như sau :**<br/>
- Start ở port 8100
- MySQL + PostgreSQL DB, sử dụng Docker để test
```shell
  ./00.start-2dbs.sh
  -----------------
  ./01.stop-2dbs.sh
```
- Script tạo data nằm trong file /resource/data.sql và được load trước khi init JPA<br/>
  (script này chạy tốt với MySQL/MySQL ở môi trường thực hoặc H2 cho Integration Tests )
```shell
  TODO: ...
```


**Tham khảo kết quả khi start WebApp & kết nối với MySQL+PostgreSQL DB:**
```shell
  TODO: ...
```

**Tham khảo kết quả khi chạy Integration Tests & sử dụng Embbed Postgres DB:**<br/>
(Trong TH này thì ta sẽ config 1 Bean DataSource dànhs riêng cho việc testing trong file `DS4TestingConfiguration`)
```shell
  TODO: ...
```

**Một số API từ Controller để tương tác vào DB**
```shell
  http://localhost:8100/user/all
  http://localhost:8100/user/findbyid?id=5
  http://localhost:8100/user/find-by-active?active=true
  http://localhost:8100/user/find-by-active?active=false
  
  http://localhost:8100/user/find-all-by-page?page=2&size=5
  http://localhost:8100/user/find-all-by-page-sort?page=2&size=5&sortField=name&sortType=desc
  
  http://localhost:8100/user/insert?numOfUsers=3
  http://localhost:8100/user/update?fromId=4&toId=10&newNamePrefix=MY-PREFIX&maxUpdateItems=2
  
  http://localhost:8100/user/sort?sortField=createDate&sortType=desc
  
  http://localhost:8100/user/custom-sql
  
  ...... còn nữa, mà lười liệt kê :) ....

```
