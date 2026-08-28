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

# Ví dụ [21.Tracing+JavaAgent+OTLP]
==============================================================

**Tao TraceID/SpanID de trace request giua cac microservices apps:**<br/>
(Lấy từ Spring Initializr)
- TraceID khong doi giua cac request
- SpanID se nam giua cac features
- user-service
  - traceID
  - spanID-01
- order-service
  - traceID
  - spanID-02
- payment-service
  - traceID
  - spanID-03

Thong tin traceID/spanID co the duoc extract nhu sau :
  SpanContext context = Span.current().getSpanContext();
  String traceId = context.getTraceId();
  String spanId = context.getSpanId();

hoac show ben trong log pattern: 
    %d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [traceId=%X{trace_id}, spanId=%X{span_id}] [%thread] %logger{36} - TDC-UserService - %msg%n

