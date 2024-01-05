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

# Ví dụ [18.WebSocket+3ChatRoom]
==============================================================

**Tham khảo**
- https://github.com/trandungchien1982/spring/tree/15.WebSocket
- https://spring.io/guides/gs/messaging-stomp-websocket/
- https://www.baeldung.com/websockets-spring
- https://medium.com/stackademic/websockets-in-spring-boot-how-to-build-real-time-applications-95a4fd0c7ec8
- https://docs.spring.io/spring-framework/docs/5.0.0.M5/spring-framework-reference/html/websocket.html
- https://www.toptal.com/java/stomp-spring-boot-websocket

**Tạo WebSocket với ví dụ về 3 ChatRoom**
- Giả lập chương trình Chat trên browser, phân thành 3 chat rooms ...
- Chat room 01: http://localhost:8056
- Chat room 02: http://localhost:8056/chat1.html
- Chat room 03: http://localhost:8056/chat3.html

**Khởi động `spring-web-socket` server (Backend side)**<br/>
- Spring App chạy ở cổng **8056**
- WebSocket Server mở cổng **8056** với endpoint: `/gs-guide-websocket` để các Clients kết nối vào<br/>
  (Sử dụng giao thức STORM)
```shell
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8056/gs-guide-websocket'
});
```

- Các Clients sẽ subscribe các topic khác nhau, mỗi topic thuộc 1 chat room: 
  - `/topic/greetings`
  - `/topic/greetings/room1`
  - `/topic/greetings/room2`

**Start Server & kết nối các clients với nhau**<br/>
Mỗi Clients sẽ có 1 name khác nhau và gửi vài` `message lên server <br/>
(JSON data và được mapping thành `ClientMessage`)<br/>
=> Tất cả các Clients đều sẽ được nhận message giống nhau từ phía server gửi về thông qua `ServerMessage`
```shell
./gradlew bootRun
-----------------------------------------------------------
> Task :MainApplication.main()

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

23:34:31.821 INFO  - Starting MainApplication using Java 17.0.8 on MacBook-Air-cua-Doan.local with PID 80684 (/Users/tdc/spring/spring-web-socket/build/classes/java/main started by tdc in /Users/tdc/spring/spring-web-socket)
23:34:31.827 INFO  - No active profile set, falling back to 1 default profile: "default"
23:34:33.935 INFO  - Tomcat initialized with port(s): 8056 (http)
23:34:33.956 INFO  - Initializing ProtocolHandler ["http-nio-8056"]
23:34:33.961 INFO  - Starting service [Tomcat]
23:34:33.962 INFO  - Starting Servlet engine: [Apache Tomcat/9.0.80]
23:34:34.133 INFO  - Initializing Spring embedded WebApplicationContext
23:34:34.134 INFO  - Root WebApplicationContext: initialization completed in 2093 ms
23:34:34.744 INFO  - Adding welcome page: class path resource [static/index.html]
23:34:34.873 WARN  - Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
23:34:34.929 INFO  - Starting ProtocolHandler ["http-nio-8056"]
23:34:34.979 INFO  - Tomcat started on port(s): 8056 (http) with context path ''
23:34:34.981 INFO  - Starting...
23:34:34.982 INFO  - BrokerAvailabilityEvent[available=true, SimpleBrokerMessageHandler [org.springframework.messaging.simp.broker.DefaultSubscriptionRegistry@795f5d51]]
23:34:34.982 INFO  - Started.
23:34:34.994 INFO  - Started MainApplication in 4.073 seconds (JVM running for 5.198)
23:34:34.996 INFO  - Start running CLI for Spring WebSocket with 3 Chat Room ...
23:34:38.205 INFO  - Initializing Spring DispatcherServlet 'dispatcherServlet'
23:34:38.205 INFO  - Initializing Servlet 'dispatcherServlet'
23:34:38.208 INFO  - Completed initialization in 3 ms
23:34:49.266 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=AAA)
23:34:49.266 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
23:35:03.522 INFO  - [ChatController] :: Received message from /chat : ClientMessage(name=BBB)
23:35:03.522 INFO  - [ChatController] :: Routing message to all subscriber of /topic/messages
23:35:16.764 INFO  - [ChatRoom1Controller] :: Received message from /chat : ClientMessage(name=User 01)
23:35:16.764 INFO  - [ChatRoom1Controller] :: Routing message to all subscriber of /topic/greetings/room1
23:35:31.223 INFO  - [ChatRoom1Controller] :: Received message from /chat : ClientMessage(name=User 02)
23:35:31.224 INFO  - [ChatRoom1Controller] :: Routing message to all subscriber of /topic/greetings/room1
23:35:34.530 INFO  - WebSocketSession[4 current WS(4)-HttpStream(0)-HttpPoll(0), 8 total, 0 closed abnormally (0 connect failure, 0 send limit, 0 transport error)], stompSubProtocol[processed CONNECT(8)-CONNECTED(8)-DISCONNECT(0)], stompBrokerRelay[null], inboundChannel[pool size = 8, active threads = 0, queued tasks = 0, completed tasks = 72], outboundChannel[pool size = 8, active threads = 0, queued tasks = 0, completed tasks = 22], sockJsScheduler[pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
23:35:53.597 INFO  - [ChatRoom1] :: Try to broadcast a new message from Server, chatIdx: 1, destination: /topic/greetings/room1
23:35:55.972 INFO  - [ChatRoom1] :: Try to broadcast a new message from Server, chatIdx: 2, destination: /topic/greetings/room1
23:36:23.466 INFO  - [ChatRoom2Controller] :: Received message from /chat : ClientMessage(name=Thanh Huyền)
23:36:23.466 INFO  - [ChatRoom2Controller] :: Routing message to all subscriber of /topic/greetings/room2
23:36:38.171 INFO  - [ChatRoom2Controller] :: Received message from /chat : ClientMessage(name=Nhật Minh)
23:36:38.171 INFO  - [ChatRoom2Controller] :: Routing message to all subscriber of /topic/greetings/room2
23:36:54.529 INFO  - [ChatRoom2] :: Try to broadcast a new message from Server, chatIdx: 1, destination: /topic/greetings/room2
23:36:56.654 INFO  - [ChatRoom2] :: Try to broadcast a new message from Server, chatIdx: 2, destination: /topic/greetings/room2

```
