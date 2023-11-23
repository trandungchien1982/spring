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

# Ví dụ [14.BeanScopes]
==============================================================
## Tham khảo
- https://www.baeldung.com/spring-bean-scopes
- https://www.baeldung.com/spring-custom-scope
- https://shareprogramming.net/hieu-ro-spring-bean-scope/#Singleton_Scope

## Ví dụ minh hoạ về việc tạo Bean theo các scope khác nhau
- **`singleton`**: 1 instance xài chung duy nhất cho toàn hệ thống
- **`prototype`**: Mỗi lần khai báo thì sẽ có 1 instance mới hoàn toàn
- _request_: 1 Http Request sẽ tạo 1 instance mới (Web-Aware)
- _session_: 1 session sẽ tạo 1 instance mới (Web-Aware)
- _application_: 1 applicationContext sẽ tạo 1 instance mới (Web-Aware)
- _websocket_: Chỉ sử dụng khi kết nối dạng `WebSocket` (Web-Aware)


## Kết quả thực thi
(App Spring Boot chạy ở port 8420)
```shell
GET http://localhost:8420/get-users
------------------------------------------------

13:52:56.296 [http-nio-8420-exec-6] INFO  - --------------------------------------------------------------
13:52:56.296 [http-nio-8420-exec-6] INFO  -  >> Try to create new request ... 
13:52:56.296 [http-nio-8420-exec-6] INFO  - [UserService] The singleBeanObject instance: SingleBeanObject(beanId=SingleBeanValue::0, beanValue=null)
13:52:56.296 [http-nio-8420-exec-6] INFO  - [UserService] The prototypeBeanObject instance: PrototypeBeanObject(beanId=PrototypeBeanID::0, beanValue=null)
13:52:56.296 [http-nio-8420-exec-6] INFO  -  >> Try to create new instance of RequestBeanObject: 5
13:52:56.297 [http-nio-8420-exec-6] INFO  - [UserService] The requestBeanObject instance: RequestBeanObject(beanId=RequestBeanObject::5, beanValue=null)
13:52:56.297 [http-nio-8420-exec-6] INFO  - [UserController] The singleBeanObject instance: SingleBeanObject(beanId=SingleBeanValue::0, beanValue=null)
13:52:56.297 [http-nio-8420-exec-6] INFO  - [UserController] The prototypeBeanObject instance: PrototypeBeanObject(beanId=PrototypeBeanID::1, beanValue=null)
13:52:56.297 [http-nio-8420-exec-6] INFO  - [UserController] The requestBeanObject instance: RequestBeanObject(beanId=RequestBeanObject::5, beanValue=null)

```
