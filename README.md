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

# Ví dụ [15.WebSocket]
==============================================================

**Tham khảo**
- https://spring.io/guides/gs/messaging-stomp-websocket/
- https://www.baeldung.com/websockets-spring
- https://medium.com/stackademic/websockets-in-spring-boot-how-to-build-real-time-applications-95a4fd0c7ec8
- https://docs.spring.io/spring-framework/docs/5.0.0.M5/spring-framework-reference/html/websocket.html
- https://www.toptal.com/java/stomp-spring-boot-websocket

**Tìm hiểu về WebSocket trong Spring**
- Config WebSocket để tạo kết nối 2 chiều (Server/Multi-Clients)
- Tạo 1 UI có HTML/JS để kết nối WebSocket đến Spring App và giả lập 1 chương trình Chat trên browser.

**Khởi động `spring-web-socket` server (Backend side)**<br/>
- Spring App chạy ở cổng **8055**
- WebSocket Server mở cổng **8055** với endpoint: `/gs-guide-websocket` để các Clients kết nối vào<br/>
  (Sử dụng giao thức STORM)
```shell
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8055/gs-guide-websocket'
});
```

- Các Clients sẽ subscribe topic: `/topic/greetings`
```shell
stompClient.subscribe('/topic/greetings', (greeting) => {
    showGreeting(JSON.parse(greeting.body).content);
});
```

**Start Server & kết nối 3 clients khác nhau**<br/>
Mỗi Clients sẽ có 1 name khác nhau và gửi vài message lên server <br/>
(JSON data và được mapping thành `ClientMessage`)<br/>
=> Tất cả các Clients đều sẽ được nhận message giống nhau từ phía server gửi về thông qua `ServerMessage`
```shell
./gradlew bootRun
-----------------------------------------------------------
> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

22:56:16.888 INFO  - Starting MainApplication using Java 17.0.8 on MacBook-Air-cua-Doan.local with PID 31131 (/Users/tdc/spring/spring-web-socket/build/classes/java/main started by tdc in /Users/tdc/spring/spring-web-socket)
22:56:16.891 INFO  - No active profile set, falling back to 1 default profile: "default"
22:56:18.560 INFO  - Tomcat initialized with port(s): 8055 (http)
22:56:18.575 INFO  - Initializing ProtocolHandler ["http-nio-8055"]
22:56:18.579 INFO  - Starting service [Tomcat]
22:56:18.579 INFO  - Starting Servlet engine: [Apache Tomcat/9.0.80]
22:56:18.740 INFO  - Initializing Spring embedded WebApplicationContext
22:56:18.740 INFO  - Root WebApplicationContext: initialization completed in 1797 ms
22:56:19.168 INFO  - Adding welcome page: class path resource [static/index.html]
22:56:19.288 WARN  - Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
22:56:19.332 INFO  - Starting ProtocolHandler ["http-nio-8055"]
22:56:19.381 INFO  - Tomcat started on port(s): 8055 (http) with context path ''
22:56:19.383 INFO  - Starting...
22:56:19.383 INFO  - BrokerAvailabilityEvent[available=true, SimpleBrokerMessageHandler [org.springframework.messaging.simp.broker.DefaultSubscriptionRegistry@3f183caa]]
22:56:19.384 INFO  - Started.
22:56:19.396 INFO  - Started MainApplication in 2.889 seconds (JVM running for 3.353)
22:56:19.398 INFO  - Start running CLI for Spring WebSocket ...
22:56:22.947 INFO  - Initializing Spring DispatcherServlet 'dispatcherServlet'
22:56:22.947 INFO  - Initializing Servlet 'dispatcherServlet'
22:56:22.948 INFO  - Completed initialization in 1 ms
22:56:39.185 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User01)
22:56:39.185 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:56:45.554 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User02)
22:56:45.555 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:56:45.894 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User02)
22:56:45.894 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:56:52.385 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User03)
22:56:52.385 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:56:55.847 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 1, destination: /topic/greetings
22:56:57.484 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 2, destination: /topic/greetings
22:56:57.866 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 3, destination: /topic/greetings
22:56:58.246 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 4, destination: /topic/greetings
22:56:58.665 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 5, destination: /topic/greetings
22:56:59.059 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 6, destination: /topic/greetings
22:56:59.450 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 7, destination: /topic/greetings
22:57:01.773 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User01)
22:57:01.773 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:57:04.531 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User02)
22:57:04.531 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:57:06.359 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User03)
22:57:06.359 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:57:07.951 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 8, destination: /topic/greetings
22:57:08.121 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 9, destination: /topic/greetings
22:57:18.422 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=User03)
22:57:18.422 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
22:57:18.982 INFO  - WebSocketSession[3 current WS(3)-HttpStream(0)-HttpPoll(0), 3 total, 0 closed abnormally (0 connect failure, 0 send limit, 0 transport error)], stompSubProtocol[processed CONNECT(3)-CONNECTED(3)-DISCONNECT(0)], stompBrokerRelay[null], inboundChannel[pool size = 8, active threads = 0, queued tasks = 0, completed tasks = 42], outboundChannel[pool size = 8, active threads = 0, queued tasks = 0, completed tasks = 50], sockJsScheduler[pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
22:57:20.341 INFO  - [ChatController] :: Try to broadcast a new message from Server, chatIdx: 10, destination: /topic/greetings
<==========---> 80% EXECUTING [2m 36s]
> :bootRun

```
