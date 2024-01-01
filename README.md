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

# Ví dụ [17.LockRecordWithAOP]
==============================================================

**Tham khảo**
- https://github.com/trandungchien1982/spring/tree/11.AOP
- https://docs.spring.io/spring-framework/docs/2.0.x/reference/aop.html

**Yêu cầu**
- Thiết kế 1 cơ chế Lock Record theo từng dòng để đảm bảo 1 lần chỉ 1 user thao tác edit/save record mà thôi
- Cần thiết kế dạng generic (Component/Aspect) để có thể tái sử dụng lại trong tương lại
- Khi User click edit 1 row thì row item đó sẽ bị Locked và các User khác không được phép edit row đó.
- Khi User edit xong & save data thì release Lock và các User khác sẽ được phép edit row bình thường
- Trên list all Record (table/grid) : 
  - các record bị Locked sẽ được highlight (xử lý real time)
  - khi 1 record bị lock/unlock bởi 1 user khác thì nó cần được reflect ngay lập tức trên List All Records

**Thiết kế**
- Giả sử ta có table 'students' cần xử lý lock record khi edit student dựa vào student.id
- Ta sẽ tạo 1 table mới để quản lý việc lock: `lock_record_management`
  - id
  - resource_name    (loại resource cần xử lý lock, TH này là `students`)
  - record_id        (refer sang bảng `students.id`)
  - created_time     (thời điểm bắt đầu lock)
  - expired_time     (thời điểm expired lock)

- Khi query (resource_name + record_id) có tồn tại
  => Record đang bị lock
- Trường hợp {now} > {expired_time}
  => Cần unlock record bằng cách xoá data khỏi bảng lock: `lock_record_management`

**Kết quả thực thi**
```shell
> Task :MainApplication.main()
```