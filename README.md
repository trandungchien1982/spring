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

# Ví dụ [13.UseSpringEvents]
==============================================================
## Tham khảo
- https://www.baeldung.com/spring-events
- https://zoltanaltfatter.com/2016/05/11/application-events-with-spring/

## Tìm hiểu Spring Events qua các ví dụ
- Tạo các custom events để giao tiếp giữa các components/modules mà không cần gọi trực tiếp. 
Ta chỉ việc tạo events & lắng nghe event đó để có những xử lý phù hợp.
- Tìm hiểu các built-in events trong Spring Framework và subscribe những event đó & thêm custom logic

## Apps/Tool
**Database MySQL 8.1.0**
```shell
Host: ubuntu.local:8306
User: root
Pass: root
DB: spring-events-db
```
## Kết quả thực thi
(App Spring Boot chạy ở port 8230)
- Theo dõi log của `ApplicationListener`
```shell
13:32:32.051 [main] INFO  - Starting ProtocolHandler ["http-nio-8230"]
13:32:32.067 [main] INFO  - Tomcat started on port(s): 8230 (http) with context path ''
13:32:32.068 [main] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent[source=org.springframework.boot.web.embedded.tomcat.TomcatWebServer@1c36ab22]
13:32:32.087 [main] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.context.event.ContextRefreshedEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@623e088f, started on Tue Nov 21 13:32:27 ICT 2023]
13:32:32.099 [main] INFO  - Started SpringEventsApplication in 4.845 seconds (JVM running for 5.432)
13:32:32.100 [main] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.boot.context.event.ApplicationStartedEvent[source=org.springframework.boot.SpringApplication@58647985]
13:32:32.111 [main] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.boot.availability.AvailabilityChangeEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@623e088f, started on Tue Nov 21 13:32:27 ICT 2023]
13:32:32.123 [main] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.boot.context.event.ApplicationReadyEvent[source=org.springframework.boot.SpringApplication@58647985]
13:32:32.133 [main] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.boot.availability.AvailabilityChangeEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@623e088f, started on Tue Nov 21 13:32:27 ICT 2023]
```

- Test publish `CustomEvent` và theo dõi việc listen `CustomEvent` bên trong `CustomListener`
```shell
GET http://localhost:8230/publish-custom-event
---------------------------------------------------------------------
FINISH publish custom event id: 1700548398530

13:33:18.495 [http-nio-8230-exec-1] INFO  - Initializing Spring DispatcherServlet 'dispatcherServlet'
13:33:18.495 [http-nio-8230-exec-1] INFO  - Initializing Servlet 'dispatcherServlet'
13:33:18.497 [http-nio-8230-exec-1] INFO  - Completed initialization in 2 ms
13:33:18.533 [http-nio-8230-exec-1] INFO  - [UserController] ----------------------------------------------------------
13:33:18.534 [http-nio-8230-exec-1] INFO  - [UserController] Publish Custom event id: 1700548398530
13:33:18.534 [http-nio-8230-exec-1] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.context.PayloadApplicationEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@623e088f, started on Tue Nov 21 13:32:27 ICT 2023]
13:33:18.552 [http-nio-8230-exec-1] INFO  - [CustomListener] :: Handle the CustomEvent: CustomEvent(eventId=ID: 1700548398530, eventMsg=MSG: 1700548398530)
13:33:21.553 [http-nio-8230-exec-1] INFO  - [UserController] Return the response string for custom id: 1700548398530
13:33:21.590 [http-nio-8230-exec-1] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: ServletRequestHandledEvent: url=[/publish-custom-event]; client=[0:0:0:0:0:0:0:1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[3083ms]; status=[OK]

```

- Test publish `SpecificEvent` và theo dõi việc listen `SpecificEvent` bên trong `CustomListener`
```shell
GET http://localhost:8230/publish-specific-event
---------------------------------------------------------------------
FINISH publish Specific event id: 1700548552824

13:35:52.828 [http-nio-8230-exec-1] INFO  - [UserController] ----------------------------------------------------------
13:35:52.829 [http-nio-8230-exec-1] INFO  - [UserController] Publish Specific event id: 1700548552824
13:35:52.829 [http-nio-8230-exec-1] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.context.PayloadApplicationEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@623e088f, started on Tue Nov 21 13:34:59 ICT 2023]
13:35:52.848 [http-nio-8230-exec-1] INFO  - [CustomListener] :: Handle the SpecificEvent: SpecificEvent(spEventId=Sp-ID: 1700548552824, spEventMsg=Sp-MSG: 1700548552824)
13:35:56.849 [http-nio-8230-exec-1] INFO  - [UserController] Return the response string for specific id: 1700548552824
13:35:56.887 [http-nio-8230-exec-1] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: ServletRequestHandledEvent: url=[/publish-specific-event]; client=[0:0:0:0:0:0:0:1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[4082ms]; status=[OK]
```

- Test publish to add a new user WITHOUT transaction
```shell
GET http://localhost:8230/publish-add-user-no-transaction
---------------------------------------------------------------------
FINISH publish add user [NO-Transaction]: 1700556810725

15:53:30.725 [http-nio-8230-exec-4] INFO  - [UserController] ----------------------------------------------------------
15:53:30.725 [http-nio-8230-exec-4] INFO  - [UserController] Try to add new user with NO transaction: 
15:53:30.726 [http-nio-8230-exec-4] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.context.PayloadApplicationEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@39fcbef6, started on Tue Nov 21 15:52:38 ICT 2023]
15:53:30.737 [http-nio-8230-exec-4] INFO  - [UserService] :: Start adding new user: 1700556810737
15:53:30.750 [http-nio-8230-exec-4] INFO  - [UserService] :: Finish persist data of user: 1700556810737
15:53:30.750 [http-nio-8230-exec-4] INFO  - [UserController] Return the response string for add new user with NO transaction  
15:53:30.754 [http-nio-8230-exec-4] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: ServletRequestHandledEvent: url=[/publish-add-user-no-transaction]; client=[0:0:0:0:0:0:0:1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[30ms]; status=[OK]

```

- Test publish to add a new user within transaction và theo dõi việc listen transaction events bên trong `TransactionsListener`
```shell
GET http://localhost:8230/publish-add-user-transaction
---------------------------------------------------------------------
FINISH publish add user [Transaction]: 1700557009112

15:56:49.112 [http-nio-8230-exec-10] INFO  - [UserController] ----------------------------------------------------------
15:56:49.114 [http-nio-8230-exec-10] INFO  - [UserController] Try to add new user within transaction: 1700557009112
15:56:49.115 [http-nio-8230-exec-10] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.context.PayloadApplicationEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@39fcbef6, started on Tue Nov 21 15:52:38 ICT 2023]
15:56:49.126 [http-nio-8230-exec-10] INFO  - [UserService] :: Start adding new user: 1700557009126
15:56:49.130 [http-nio-8230-exec-10] INFO  - [UserService] :: Finish persist data of user: 1700557009126
15:56:49.130 [http-nio-8230-exec-10] INFO  - [UserController] Return the response string for add new user within transaction  
15:56:49.131 [http-nio-8230-exec-10] INFO  - [TransactionsListener] :: before commit: CHTEvent(eventTransId=eventTransID: 1700557009112, eventTransMsg=eventTransMsg: 1700557009112), currentThread: Thread[http-nio-8230-exec-10,5,main]
15:56:49.648 [http-nio-8230-exec-10] INFO  - [TransactionsListener] :: after commit: CHTEvent(eventTransId=eventTransID: 1700557009112, eventTransMsg=eventTransMsg: 1700557009112), currentThread: Thread[http-nio-8230-exec-10,5,main]
15:56:50.149 [http-nio-8230-exec-10] INFO  - [TransactionsListener] :: after completion: CHTEvent(eventTransId=eventTransID: 1700557009112, eventTransMsg=eventTransMsg: 1700557009112), currentThread: Thread[http-nio-8230-exec-10,5,main]
15:56:50.655 [http-nio-8230-exec-10] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: ServletRequestHandledEvent: url=[/publish-add-user-transaction]; client=[0:0:0:0:0:0:0:1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[1549ms]; status=[OK]

```

- Test publish to add a new user with rollback và theo dõi việc listen transaction events bên trong `TransactionsListener`
```shell
GET http://localhost:8230/publish-add-user-rollback
---------------------------------------------------------------------
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue Nov 21 16:09:49 ICT 2023
There was an unexpected error (type=Internal Server Error, status=500).


16:09:47.834 [http-nio-8230-exec-2] INFO  - [UserController] ----------------------------------------------------------
16:09:47.834 [http-nio-8230-exec-2] INFO  - [UserController] Try to add new user with rollback: 1700557787834
16:09:47.834 [http-nio-8230-exec-2] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: org.springframework.context.PayloadApplicationEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@39fcbef6, started on Tue Nov 21 16:09:24 ICT 2023]
16:09:47.845 [http-nio-8230-exec-2] INFO  - [UserService] :: Start adding new user: 1700557787845
16:09:47.853 [http-nio-8230-exec-2] INFO  - [UserService] :: Finish persist data of user: 1700557787845
16:09:47.854 [http-nio-8230-exec-2] ERROR - [UserController] :: Exception occur {}
java.sql.SQLException: Force error in SQL of adding new user :) 
	at spring_events.services.UserService.addUserWithError(UserService.java:49)
	at spring_events.services.UserService$$FastClassBySpringCGLIB$$d344e93d.invoke(<generated>)
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:792)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:762)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:762)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:707)
	at spring_events.services.UserService$$EnhancerBySpringCGLIB$$546f6b79.addUserWithError(<generated>)
	at spring_events.controllers.UserController.publishAddUserWithRollback(UserController.java:112)
	at spring_events.controllers.UserController$$FastClassBySpringCGLIB$$7943a4a1.invoke(<generated>)
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:792)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:762)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:762)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:707)
	at spring_events.controllers.UserController$$EnhancerBySpringCGLIB$$3ab9dc25.publishAddUserWithRollback(<generated>)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1072)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:965)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:529)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:623)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:168)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:481)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:390)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1790)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.base/java.lang.Thread.run(Thread.java:829)
16:09:47.855 [http-nio-8230-exec-2] INFO  - [UserController] Return the response string for add new user with Rollback
16:09:47.855 [http-nio-8230-exec-2] INFO  - [TransactionsListener] :: before commit: CHTEvent(eventTransId=eventTransID: 1700557787834, eventTransMsg=eventTransMsg: 1700557787834), currentThread: Thread[http-nio-8230-exec-2,5,main]
16:09:48.358 [http-nio-8230-exec-2] INFO  - [TransactionsListener] :: after completion: CHTEvent(eventTransId=eventTransID: 1700557787834, eventTransMsg=eventTransMsg: 1700557787834), currentThread: Thread[http-nio-8230-exec-2,5,main]
16:09:48.858 [http-nio-8230-exec-2] INFO  - [TransactionsListener] :: after Rollback : CHTEvent(eventTransId=eventTransID: 1700557787834, eventTransMsg=eventTransMsg: 1700557787834), currentThread: Thread[http-nio-8230-exec-2,5,main]
16:09:49.359 [http-nio-8230-exec-2] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: ServletRequestHandledEvent: url=[/publish-add-user-rollback]; client=[0:0:0:0:0:0:0:1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[1529ms]; status=[failed: org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only]
16:09:49.370 [http-nio-8230-exec-2] ERROR - Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only] with root cause
org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:752)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:711)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:654)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:407)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:762)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:707)
	at spring_events.controllers.UserController$$EnhancerBySpringCGLIB$$3ab9dc25.publishAddUserWithRollback(<generated>)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1072)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:965)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:529)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:623)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:168)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:481)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:390)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1790)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.base/java.lang.Thread.run(Thread.java:829)
16:09:49.377 [http-nio-8230-exec-2] INFO  - [ApplicationListener] :: Handle the ApplicationEvent: ServletRequestHandledEvent: url=[/error]; client=[0:0:0:0:0:0:0:1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[5ms]; status=[OK]

```