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

# Ví dụ [22.OOM-GC-Tests]
==============================================================

**Giả lập lỗi OOM - Out Of Memory Exception va config để xuất ra file .hprof và GC information  :**<br/>
- Start ở port 8120
- Các API xử lý tăng Heap Size trong RAM bằng cách thêm String line (1MB) liên tục vào static List<String>
  - GET - http://localhost:8120/main/increaseRAM?loopTimes=1000
  - GET - http://localhost:8120/sub/increaseRAM?loopTimes=1000
  - GET - http://localhost:8120/vertical/increaseRAM?loopTimes=1000
- Trong file build.gradle, task bootRun đã được customize:
  - Limit total 1GB RAM
  - HeapDumpOnOutOfMemoryError ...
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

