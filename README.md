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

# Ví dụ [11.AOP]
==============================================================

**Tham khảo**
- https://www.baeldung.com/spring-aop
- https://www.baeldung.com/spring-aop-annotation
- https://www.javainuse.com/spring/spring-boot-aop
- https://howtodoinjava.com/spring-aop-tutorial/

**Tìm hiểu về các khái niệm cơ bản trong AOP (Aspect-Oriented Programming) - Lập trình hướng khía cạnh**
- Aspect: Toàn bộ code liên quan đến phần xử lý AOP đã được module hóa
- JoinPoint: 1 vị trí cụ thể khi execution app, ở đây sẽ refer đến vị trí của các method được gọi
- PointCut: 1 pattern mô tả những vị trí cần xử lý (JoinPoint)
- Advice: 1 event cụ thể xảy ra ứng với 1 JointPoint, vd như before/after/throw exception/...<br/>
  (nó còn có 1 kiểu gọi khác là interceptor)

**Ứng dụng các khái niệm vào ví dụ cụ thể (Ở đây là ghi logs)**
- Aspect: Các classes nằm trong package `aop.aspect_config`
  - Aspect01Before đang handle 1 Advice dạng @Before
  - Aspect02After đang handle 1 Advice dạng @After
- `@Before(value = "execution(* aop.services.EmployeeService.*(..))")`
  - Mô tả advice `Before`
  - PointCut có phần filter là `execution(* aop.services.EmployeeService.*(..))`
  - JointPoint là khi method thuộc class aop.services.EmployeeService được invoke.
- Chốt lại, là khi 1 method nào đó của class `EmployeeService` được invoke <br/>
  thì ta sẽ gọi logic xử lý nằm trong Aspect01Before.beforeAdvice()

- Tương tự cho phần `Aspect02After, Aspect03AfterReturn, Aspect04Around, Aspect05AfterThrowing`


**Kết quả thực thi**
```shell
> Task :MainApplication.main()

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

16:26:43.754 INFO  - Starting MainApplication using Java 11 on DESKTOP-R6P2IB2 with PID 10436 (D:\Projects\spring\AOP\build\classes\java\main started by tdc in D:\Projects\spring\AOP)
16:26:43.756 INFO  - No active profile set, falling back to 1 default profile: "default"
16:26:44.654 INFO  - Started MainApplication in 1.373 seconds (JVM running for 1.919)
16:26:44.656 INFO  - 
------------------------ Research AOP ... 
16:26:44.660 INFO  - 
16:26:44.661 INFO  - Before method:Employee aop.services.EmployeeService.createEmployee(String,String)
16:26:44.661 INFO  -  -- [Before] joinPoint.signature.name:createEmployee
16:26:44.661 INFO  -  -- [Before] joinPoint.kind: method-execution
16:26:44.662 INFO  -  -- [Before] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@167279d1
16:26:44.662 INFO  -  -- [Before] joinPoint.staticPart: execution(Employee aop.services.EmployeeService.createEmployee(String,String))
16:26:44.662 INFO  -  -- [Before] Current args : [EmpName01, EmpId01]
16:26:44.662 INFO  -  -- args.length : 2
16:26:44.662 INFO  -  ---- current arg: EmpName01, type: java.lang.String
16:26:44.662 INFO  -  ---- current arg: EmpId01, type: java.lang.String
16:26:44.662 INFO  - 
16:26:44.662 INFO  - Around method:Employee aop.services.EmployeeService.createEmployee(String,String)
16:26:44.662 INFO  -  -- [Around] joinPoint.signature.name:createEmployee
16:26:44.662 INFO  -  -- [Around] joinPoint.kind: method-execution
16:26:44.662 INFO  -  -- [Around] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@138caeca
16:26:44.662 INFO  -  -- [Around] joinPoint.staticPart: execution(Employee aop.services.EmployeeService.createEmployee(String,String))
16:26:44.662 INFO  -  -- [Around] joinPoint.args : [EmpName01, EmpId01]
16:26:44.662 INFO  -  -- args.length : 2
16:26:44.662 INFO  -  ---- current arg: EmpName01, type: java.lang.String
16:26:44.663 INFO  -  ---- current arg: EmpId01, type: java.lang.String
16:26:44.886 INFO  -  .. Finish process of createEmployee(...)
16:26:44.886 INFO  -  -- [Around] Total exec time : 223 millis
16:26:44.886 INFO  -  -- [Around] Result : Employee(empId=EmpId01, name=EmpName01)
16:26:44.886 INFO  - 
16:26:44.886 INFO  - After returning method:Employee aop.services.EmployeeService.createEmployee(String,String)
16:26:44.886 INFO  -  -- [AfterR] joinPoint.signature.name:createEmployee
16:26:44.886 INFO  -  -- [AfterR] joinPoint.kind: method-execution
16:26:44.886 INFO  -  -- [AfterR] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@167279d1
16:26:44.886 INFO  -  -- [AfterR] joinPoint.staticPart: execution(Employee aop.services.EmployeeService.createEmployee(String,String))
16:26:44.886 INFO  -  -- [AfterR] joinPoint.args : [EmpName01, EmpId01]
16:26:44.886 INFO  -  -- args.length : 2
16:26:44.886 INFO  -  ---- current arg: EmpName01, type: java.lang.String
16:26:44.886 INFO  -  ---- current arg: EmpId01, type: java.lang.String
16:26:44.887 INFO  - 
16:26:44.887 INFO  - After method:Employee aop.services.EmployeeService.createEmployee(String,String)
16:26:44.887 INFO  -  -- [After] joinPoint.signature.name:createEmployee
16:26:44.887 INFO  -  -- [After] joinPoint.kind: method-execution
16:26:44.887 INFO  -  -- [After] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@167279d1
16:26:44.887 INFO  -  -- [After] joinPoint.staticPart: execution(Employee aop.services.EmployeeService.createEmployee(String,String))
16:26:44.887 INFO  -  -- [After] joinPoint.args : [EmpName01, EmpId01]
16:26:44.887 INFO  -  -- args.length : 2
16:26:44.887 INFO  -  ---- current arg: EmpName01, type: java.lang.String
16:26:44.887 INFO  -  ---- current arg: EmpId01, type: java.lang.String
16:26:44.887 INFO  - 
16:26:44.887 INFO  - Before method:void aop.services.EmployeeService.updateEmployee(String,Employee,int)
16:26:44.887 INFO  -  -- [Before] joinPoint.signature.name:updateEmployee
16:26:44.887 INFO  -  -- [Before] joinPoint.kind: method-execution
16:26:44.887 INFO  -  -- [Before] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@552ed807
16:26:44.887 INFO  -  -- [Before] joinPoint.staticPart: execution(void aop.services.EmployeeService.updateEmployee(String,Employee,int))
16:26:44.888 INFO  -  -- [Before] Current args : [curEmpId, Employee(empId=null, name=null), 123]
16:26:44.888 INFO  -  -- args.length : 3
16:26:44.888 INFO  -  ---- current arg: curEmpId, type: java.lang.String
16:26:44.888 INFO  -  ---- current arg: Employee(empId=null, name=null), type: aop.entities.Employee
16:26:44.888 INFO  -  ---- current arg: 123, type: java.lang.Integer
16:26:44.888 INFO  - 
16:26:44.888 INFO  - Around method:void aop.services.EmployeeService.updateEmployee(String,Employee,int)
16:26:44.888 INFO  -  -- [Around] joinPoint.signature.name:updateEmployee
16:26:44.888 INFO  -  -- [Around] joinPoint.kind: method-execution
16:26:44.888 INFO  -  -- [Around] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@3971f0fe
16:26:44.888 INFO  -  -- [Around] joinPoint.staticPart: execution(void aop.services.EmployeeService.updateEmployee(String,Employee,int))
16:26:44.888 INFO  -  -- [Around] joinPoint.args : [curEmpId, Employee(empId=null, name=null), 123]
16:26:44.888 INFO  -  -- args.length : 3
16:26:44.888 INFO  -  ---- current arg: curEmpId, type: java.lang.String
16:26:44.888 INFO  -  ---- current arg: Employee(empId=null, name=null), type: aop.entities.Employee
16:26:44.888 INFO  -  ---- current arg: 123, type: java.lang.Integer
16:26:44.996 INFO  -  .. Finish process of updateEmployee(...)
16:26:44.996 INFO  -  -- [Around] Total exec time : 108 millis
16:26:44.996 INFO  -  -- [Around] Result : null
16:26:44.996 INFO  - 
16:26:44.996 INFO  - After returning method:void aop.services.EmployeeService.updateEmployee(String,Employee,int)
16:26:44.996 INFO  -  -- [AfterR] joinPoint.signature.name:updateEmployee
16:26:44.996 INFO  -  -- [AfterR] joinPoint.kind: method-execution
16:26:44.996 INFO  -  -- [AfterR] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@552ed807
16:26:44.996 INFO  -  -- [AfterR] joinPoint.staticPart: execution(void aop.services.EmployeeService.updateEmployee(String,Employee,int))
16:26:44.996 INFO  -  -- [AfterR] joinPoint.args : [curEmpId, Employee(empId=curEmpId, name=NameWithAge: 123), 123]
16:26:44.996 INFO  -  -- args.length : 3
16:26:44.996 INFO  -  ---- current arg: curEmpId, type: java.lang.String
16:26:44.996 INFO  -  ---- current arg: Employee(empId=curEmpId, name=NameWithAge: 123), type: aop.entities.Employee
16:26:44.996 INFO  -  ---- current arg: 123, type: java.lang.Integer
16:26:44.996 INFO  - 
16:26:44.996 INFO  - After method:void aop.services.EmployeeService.updateEmployee(String,Employee,int)
16:26:44.996 INFO  -  -- [After] joinPoint.signature.name:updateEmployee
16:26:44.996 INFO  -  -- [After] joinPoint.kind: method-execution
16:26:44.996 INFO  -  -- [After] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@552ed807
16:26:44.997 INFO  -  -- [After] joinPoint.staticPart: execution(void aop.services.EmployeeService.updateEmployee(String,Employee,int))
16:26:44.997 INFO  -  -- [After] joinPoint.args : [curEmpId, Employee(empId=curEmpId, name=NameWithAge: 123), 123]
16:26:44.997 INFO  -  -- args.length : 3
16:26:44.997 INFO  -  ---- current arg: curEmpId, type: java.lang.String
16:26:44.997 INFO  -  ---- current arg: Employee(empId=curEmpId, name=NameWithAge: 123), type: aop.entities.Employee
16:26:44.997 INFO  -  ---- current arg: 123, type: java.lang.Integer
16:26:44.997 INFO  - 
16:26:44.997 INFO  - Before method:void aop.services.EmployeeService.deleteEmployee(String)
16:26:44.997 INFO  -  -- [Before] joinPoint.signature.name:deleteEmployee
16:26:44.997 INFO  -  -- [Before] joinPoint.kind: method-execution
16:26:44.997 INFO  -  -- [Before] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@23940f86
16:26:44.999 INFO  -  -- [Before] joinPoint.staticPart: execution(void aop.services.EmployeeService.deleteEmployee(String))
16:26:44.999 INFO  -  -- [Before] Current args : [EmpId02]
16:26:44.999 INFO  -  -- args.length : 1
16:26:44.999 INFO  -  ---- current arg: EmpId02, type: java.lang.String
16:26:44.999 INFO  - 
16:26:44.999 INFO  - Around method:void aop.services.EmployeeService.deleteEmployee(String)
16:26:44.999 INFO  -  -- [Around] joinPoint.signature.name:deleteEmployee
16:26:44.999 INFO  -  -- [Around] joinPoint.kind: method-execution
16:26:44.999 INFO  -  -- [Around] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@66153688
16:26:45.000 INFO  -  -- [Around] joinPoint.staticPart: execution(void aop.services.EmployeeService.deleteEmployee(String))
16:26:45.000 INFO  -  -- [Around] joinPoint.args : [EmpId02]
16:26:45.000 INFO  -  -- args.length : 1
16:26:45.000 INFO  -  ---- current arg: EmpId02, type: java.lang.String
16:26:45.012 INFO  -  .. Finish process of deleteEmployee(...)
16:26:45.012 INFO  -  -- [Around] Total exec time : 12 millis
16:26:45.012 INFO  -  -- [Around] Result : null
16:26:45.012 INFO  - 
16:26:45.012 INFO  - After returning method:void aop.services.EmployeeService.deleteEmployee(String)
16:26:45.012 INFO  -  -- [AfterR] joinPoint.signature.name:deleteEmployee
16:26:45.012 INFO  -  -- [AfterR] joinPoint.kind: method-execution
16:26:45.012 INFO  -  -- [AfterR] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@23940f86
16:26:45.012 INFO  -  -- [AfterR] joinPoint.staticPart: execution(void aop.services.EmployeeService.deleteEmployee(String))
16:26:45.012 INFO  -  -- [AfterR] joinPoint.args : [EmpId02]
16:26:45.012 INFO  -  -- args.length : 1
16:26:45.012 INFO  -  ---- current arg: EmpId02, type: java.lang.String
16:26:45.012 INFO  - 
16:26:45.013 INFO  - After method:void aop.services.EmployeeService.deleteEmployee(String)
16:26:45.013 INFO  -  -- [After] joinPoint.signature.name:deleteEmployee
16:26:45.013 INFO  -  -- [After] joinPoint.kind: method-execution
16:26:45.013 INFO  -  -- [After] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@23940f86
16:26:45.013 INFO  -  -- [After] joinPoint.staticPart: execution(void aop.services.EmployeeService.deleteEmployee(String))
16:26:45.013 INFO  -  -- [After] joinPoint.args : [EmpId02]
16:26:45.013 INFO  -  -- args.length : 1
16:26:45.013 INFO  -  ---- current arg: EmpId02, type: java.lang.String
16:26:45.013 INFO  - 
16:26:45.013 INFO  - Before method:void aop.services.EmployeeService.executeWithException(String)
16:26:45.014 INFO  -  -- [Before] joinPoint.signature.name:executeWithException
16:26:45.014 INFO  -  -- [Before] joinPoint.kind: method-execution
16:26:45.014 INFO  -  -- [Before] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@455824ad
16:26:45.014 INFO  -  -- [Before] joinPoint.staticPart: execution(void aop.services.EmployeeService.executeWithException(String))
16:26:45.014 INFO  -  -- [Before] Current args : [testEmpId]
16:26:45.014 INFO  -  -- args.length : 1
16:26:45.014 INFO  -  ---- current arg: testEmpId, type: java.lang.String
16:26:45.014 INFO  - 
16:26:45.014 INFO  - Around method:void aop.services.EmployeeService.executeWithException(String)
16:26:45.014 INFO  -  -- [Around] joinPoint.signature.name:executeWithException
16:26:45.014 INFO  -  -- [Around] joinPoint.kind: method-execution
16:26:45.014 INFO  -  -- [Around] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@7318daf8
16:26:45.014 INFO  -  -- [Around] joinPoint.staticPart: execution(void aop.services.EmployeeService.executeWithException(String))
16:26:45.014 INFO  -  -- [Around] joinPoint.args : [testEmpId]
16:26:45.014 INFO  -  -- args.length : 1
16:26:45.014 INFO  -  ---- current arg: testEmpId, type: java.lang.String
16:26:45.019 INFO  - 
16:26:45.019 INFO  - After throwing method:void aop.services.EmployeeService.executeWithException(String)
16:26:45.019 INFO  -  -- [AfterThr] joinPoint.signature.name:executeWithException
16:26:45.019 INFO  -  -- [AfterThr] joinPoint.kind: method-execution
16:26:45.019 INFO  -  -- [AfterThr] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@455824ad
16:26:45.019 INFO  -  -- [AfterThr] joinPoint.staticPart: execution(void aop.services.EmployeeService.executeWithException(String))
16:26:45.019 INFO  -  -- [AfterThr] joinPoint.args : [testEmpId]
16:26:45.019 INFO  -  -- args.length : 1
16:26:45.019 INFO  -  ---- current arg: testEmpId, type: java.lang.String
16:26:45.019 INFO  - 
16:26:45.019 INFO  - After method:void aop.services.EmployeeService.executeWithException(String)
16:26:45.019 INFO  -  -- [After] joinPoint.signature.name:executeWithException
16:26:45.019 INFO  -  -- [After] joinPoint.kind: method-execution
16:26:45.019 INFO  -  -- [After] joinPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@455824ad
16:26:45.019 INFO  -  -- [After] joinPoint.staticPart: execution(void aop.services.EmployeeService.executeWithException(String))
16:26:45.019 INFO  -  -- [After] joinPoint.args : [testEmpId]
16:26:45.019 INFO  -  -- args.length : 1
16:26:45.019 INFO  -  ---- current arg: testEmpId, type: java.lang.String
```