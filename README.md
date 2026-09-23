# spring
Các ví dụ liên quan đến Spring/SpringBoot từ cơ bản đến nâng cao<br/>
Mỗi nhánh trong Repo sẽ là 1 ví dụ/ giải pháp/ project mẫu trong Spring

# Môi trường phát triển
- Spring Boot 3.x
- JDK 21

# Build Tools sử dụng
- Maven + Gradle
- Intelij IDEA

# Folder liên quan trên Windows
```
D:\Projects\spring
```

==============================================================

# Ví dụ [23.Custom+Prometheus+Metrics]
==============================================================

**Viết Custom Prometheus Metrics trong SpringBoot để theo dõi một số value quan trọng :**<br/>
- tdc_count_transaction_total
- tdc_count_threads_total

**API liên quan**
- http://localhost:8100/metrics
- http://localhost:8100/transaction
- http://localhost:8100/thread
- http://localhost:8100/actuator/prometheus

```
tasks.named('bootRun') {
    jvmArgs = [
            '-Xms512m',
            '-Xmx1g',
            '-XX:+UseG1GC',

            '-XX:+HeapDumpOnOutOfMemoryError',
            '-XX:HeapDumpPath=./heapdump.hprof',

            '-Xlog:gc*=trace:file=./gc.log:time,uptime,level,tags'
    ]
}
```

- File Dump RAM cho OOM và GC nằm ở root folder 
- Theo dõi Console Logs để thấy lỗi OutOfMemoryException

