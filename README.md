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

# Ví dụ [16.CORS]
==============================================================

**Tham khảo**
- https://viblo.asia/p/cors-la-gi-Qbq5Q0j3lD8
- https://viblo.asia/p/tim-hieu-ve-cross-origin-resource-sharing-cors-Az45bGWqKxY
- https://tvd12.com/cau-hinh-cors-domain/
- https://howtodoinjava.com/spring-boot2/spring-cors-configuration/

**Tìm hiểu về CORS (Cross Origin Resource Sharing) và cách config CORS trong Spring**
- Các request đến từ cùng 1 domain sẽ được chấp nhận qua SOP (Same Origin Policy)
- Các request đến từ domain khác muốn được server BE chấp nhận thì phải follow theo rule của CORS<br/>
Các config này được trả về thông qua Response Header, bao gồm : 
  - Access-Controll-Allow-Origin: * (Chấp nhận tất cả requests)
  - Access-Control-Allow-Origin: https://test.com <br/>
    (Chỉ chấp nhận request từ https://test.com)
- Access-Control-Allow_Headers: x-authentication-token, Authorization, Content-Type <br/>
  (Chỉ chấp nhận requests có chứa các headers như đã liệt kê)

**Pre-flight requests**
- Là request nhá hàng do browser tự thực hiện trước khi gửi request chính
- Request này sẽ có dạng OPTIONS

