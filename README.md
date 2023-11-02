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

# Ví dụ [08.ApplyConfigsProps]
==============================================================

**Tham khảo**
- https://www.baeldung.com/configuration-properties-in-spring-boot
- https://www.baeldung.com/properties-with-spring
- https://www.baeldung.com/spring-tests-override-properties
- https://www.baeldung.com/spring-dynamicpropertysource

**Ta sẽ tạo 1 App Spring Boot minh họa các đọc các config values từ nhiều nguồn khác nhau:**<br/>
- Start ở port 8100
- Spring Boot version : 2.7.16
- JDK: 11

**Ứng dụng RESTful API có các endpoints sau đây:**
  - GET    http://localhost:8100/env/list <br />
  => Sử dụng object @Autowire Environment env;
  
  - GET    http://localhost:8100/system-envs/list <br />
  => Sử dụng @Value kèm theo config dạng biến môi trường, <br /> 
     vd: VALUE_03_SYSTEM_PATH, VALUE_04_SYSTEM_PATH
  
  - GET    http://localhost:8100/values/list <br/>
  => Sử dụng @Value thuần
  
  - GET    http://localhost:8100/server/list <br />
  => Sử dụng @Configuration + @ConfigurationProperties để mapping các properties vào trong POJO Java Class

**Apply key/values vào trong TestCases**
- ApplyConfigsApplicationTests <br />
=> Sử dụng profile `test` và đọc các file properties như sau:
  - application.properties
  - server.properties
  - application-test.properties

- OverwriteConfigsTests <br />
=> Tiến hành overwrite lại một số key/value tuỳ chỉnh
  - test.first.data=Overwrite of [TestFirstData]
  - value02.sub=Overwrite of [Value02 Sub + Content
  - Sau đó là các key/values trong file `overwrite-test.properties`

- DynamicConfigsTests <br />
=> Lấy các giá trị `value` ngẫu nhiên cho các `key` chỉ định
  - test.second.data
  - value04.system.env.path


**Kết quả Console Log**
```shell
> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE
> Task :compileTestJava
> Task :processTestResources NO-SOURCE
> Task :testClasses
> Task :test
15:38:32.849 [Test worker] INFO  - Neither @ContextConfiguration nor @ContextHierarchy found for test class [demo.applyconfigs.ApplyConfigsApplicationTests], using SpringBootContextLoader
15:38:32.878 [Test worker] INFO  - Could not detect default resource locations for test class [demo.applyconfigs.ApplyConfigsApplicationTests]: no resource found for suffixes {-context.xml, Context.groovy}.
15:38:32.882 [Test worker] INFO  - Could not detect default configuration classes for test class [demo.applyconfigs.ApplyConfigsApplicationTests]: ApplyConfigsApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
15:38:33.194 [Test worker] INFO  - Found @SpringBootConfiguration demo.applyconfigs.ApplyConfigsApplication for test class demo.applyconfigs.ApplyConfigsApplicationTests
15:38:33.539 [Test worker] INFO  - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]
15:38:33.561 [Test worker] INFO  - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@2c177f9e, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@5db4c359, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@209775a9, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@18e7143f, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@f9b7332, org.springframework.test.context.support.DirtiesContextTestExecutionListener@74cec793, org.springframework.test.context.event.EventPublishingTestExecutionListener@6fefce9e, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@4f8969b0, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@1bdf8190, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@192f2f27, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@8a589a2, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@c65a5ef, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@6b5176f2]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

15:38:34.204 [Test worker] INFO  - Starting ApplyConfigsApplicationTests using Java 17.0.8 on MacBook-Air-cua-Doan.local with PID 75135 (started by tdc in /Users/tdc/spring/apply-configs-props)
15:38:34.206 [Test worker] INFO  - The following 1 profile is active: "test"
15:38:36.145 [Test worker] INFO  - Started ApplyConfigsApplicationTests in 2.511 seconds (JVM running for 5.166)
15:38:36.147 [Test worker] INFO  - Start running some initialize CLI ...
15:38:36.811 [Test worker] INFO  -  >> Show all data in Test Cases ...
15:38:36.813 [Test worker] INFO  - lstEnvs: [test, default, server.port = 9100, test.first.data = ForTest[First Data], test.second.data = [Testing of 2nd Data], test.3rd.data = [NotFound:test.3rd.data], spring.datasource.url = null, spring.datasource.username = null, spring.datasource.password = null]
15:38:36.813 [Test worker] INFO  - lstSystemEnvs: [value03EnvPath = ForTest[Value 03 Content], value04EnvPath = DefaultValue FOUR-th of SystemPath]
15:38:36.814 [Test worker] INFO  - lstValues: [value01 = Value 01, value02 = ForTest[Value 02 Content], value02.sub = [Value 02 Content + Sub Data]]
15:38:36.841 [Test worker] INFO  - Neither @ContextConfiguration nor @ContextHierarchy found for test class [demo.applyconfigs.DynamicConfigsTests], using SpringBootContextLoader
15:38:36.845 [Test worker] INFO  - Could not detect default resource locations for test class [demo.applyconfigs.DynamicConfigsTests]: no resource found for suffixes {-context.xml, Context.groovy}.
15:38:36.845 [Test worker] INFO  - Could not detect default configuration classes for test class [demo.applyconfigs.DynamicConfigsTests]: DynamicConfigsTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
15:38:36.853 [Test worker] INFO  - Found @SpringBootConfiguration demo.applyconfigs.ApplyConfigsApplication for test class demo.applyconfigs.DynamicConfigsTests
15:38:36.855 [Test worker] INFO  - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]
15:38:36.857 [Test worker] INFO  - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@1de13f34, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@7e191fda, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@6562cc23, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@2ce524d2, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@3869a6e5, org.springframework.test.context.support.DirtiesContextTestExecutionListener@e3899fd, org.springframework.test.context.event.EventPublishingTestExecutionListener@7d484fcd, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@788e3702, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@d25e878, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@47187f50, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@78116659, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@712e787e, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@53ea380b]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

15:38:36.903 [Test worker] INFO  -  >> CALL setting Dynamic Source ...
15:38:36.907 [Test worker] INFO  - Starting DynamicConfigsTests using Java 17.0.8 on MacBook-Air-cua-Doan.local with PID 75135 (started by tdc in /Users/tdc/spring/apply-configs-props)
15:38:36.908 [Test worker] INFO  - The following 1 profile is active: "test"
15:38:37.693 [Test worker] INFO  - Started DynamicConfigsTests in 0.831 seconds (JVM running for 6.714)
15:38:37.694 [Test worker] INFO  - Start running some initialize CLI ...
15:38:37.707 [Test worker] INFO  -  >> In Dynamic TestCase 01 ...
15:38:37.707 [Test worker] INFO  -  >> Show all data with Dynamic Config some key/values in testing ...
15:38:37.709 [Test worker] INFO  - lstEnvs: [test, default, server.port = 9100, test.first.data = ForTest[First Data], test.second.data = MyCustom::test.second.data - 014fb742-c8b3-4133-99ca-0c23c70d8346, test.3rd.data = [NotFound:test.3rd.data], spring.datasource.url = DataSourceURL::014fb742-c8b3-4133-99ca-0c23c70d8346, spring.datasource.username = DataSourceUSR::014fb742-c8b3-4133-99ca-0c23c70d8346, spring.datasource.password = DataSourcePWD::014fb742-c8b3-4133-99ca-0c23c70d8346]
15:38:37.709 [Test worker] INFO  - lstSystemEnvs: [value03EnvPath = ForTest[Value 03 Content], value04EnvPath = MyCustom::value04.system.env.path - 014fb742-c8b3-4133-99ca-0c23c70d8346]
15:38:37.709 [Test worker] INFO  - lstValues: [value01 = Value 01, value02 = ForTest[Value 02 Content], value02.sub = [Value 02 Content + Sub Data]]
15:38:37.722 [Test worker] INFO  -  >> In Dynamic TestCase 02 ...
15:38:37.722 [Test worker] INFO  -  >> Show all data with Dynamic Config some key/values in testing ...
15:38:37.723 [Test worker] INFO  - lstEnvs: [test, default, server.port = 9100, test.first.data = ForTest[First Data], test.second.data = MyCustom::test.second.data - 014fb742-c8b3-4133-99ca-0c23c70d8346, test.3rd.data = [NotFound:test.3rd.data], spring.datasource.url = DataSourceURL::014fb742-c8b3-4133-99ca-0c23c70d8346, spring.datasource.username = DataSourceUSR::014fb742-c8b3-4133-99ca-0c23c70d8346, spring.datasource.password = DataSourcePWD::014fb742-c8b3-4133-99ca-0c23c70d8346]
15:38:37.723 [Test worker] INFO  - lstSystemEnvs: [value03EnvPath = ForTest[Value 03 Content], value04EnvPath = MyCustom::value04.system.env.path - 014fb742-c8b3-4133-99ca-0c23c70d8346]
15:38:37.723 [Test worker] INFO  - lstValues: [value01 = Value 01, value02 = ForTest[Value 02 Content], value02.sub = [Value 02 Content + Sub Data]]
15:38:37.732 [Test worker] INFO  - Neither @ContextConfiguration nor @ContextHierarchy found for test class [demo.applyconfigs.OverwriteConfigsTests], using SpringBootContextLoader
15:38:37.733 [Test worker] INFO  - Could not detect default resource locations for test class [demo.applyconfigs.OverwriteConfigsTests]: no resource found for suffixes {-context.xml, Context.groovy}.
15:38:37.733 [Test worker] INFO  - Could not detect default configuration classes for test class [demo.applyconfigs.OverwriteConfigsTests]: OverwriteConfigsTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
15:38:37.739 [Test worker] INFO  - Found @SpringBootConfiguration demo.applyconfigs.ApplyConfigsApplication for test class demo.applyconfigs.OverwriteConfigsTests
15:38:37.741 [Test worker] INFO  - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]
15:38:37.749 [Test worker] INFO  - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@46612bfc, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@4f213a2, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@25699aa7, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@5a47730c, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@15369d73, org.springframework.test.context.support.DirtiesContextTestExecutionListener@1cde374, org.springframework.test.context.event.EventPublishingTestExecutionListener@6818fd48, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@9263c54, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@28daf506, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@4662752a, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@693f2213, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@514377fc, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@2e4eda17]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

15:38:37.855 [Test worker] INFO  - Starting OverwriteConfigsTests using Java 17.0.8 on MacBook-Air-cua-Doan.local with PID 75135 (started by tdc in /Users/tdc/spring/apply-configs-props)
15:38:37.857 [Test worker] INFO  - The following 1 profile is active: "test"
15:38:38.474 [Test worker] INFO  - Started OverwriteConfigsTests in 0.72 seconds (JVM running for 7.495)
15:38:38.476 [Test worker] INFO  - Start running some initialize CLI ...
15:38:38.486 [Test worker] INFO  -  >> Overwrite some key/values in testing ...
15:38:38.488 [Test worker] INFO  - lstEnvs: [test, default, server.port = 9100, test.first.data = Overwrite of [TestFirstData], test.second.data = [Testing of 2nd Data], test.3rd.data = [NotFound:test.3rd.data], spring.datasource.url = null, spring.datasource.username = null, spring.datasource.password = null]
15:38:38.489 [Test worker] INFO  - lstSystemEnvs: [value03EnvPath = Overwrite-File-[Value03 + SystemPath], value04EnvPath = Overwrite-File-[Value04 + SystemPath]]
15:38:38.490 [Test worker] INFO  - lstValues: [value01 = Value 01, value02 = ForTest[Value 02 Content], value02.sub = Overwrite of [Value02 Sub + Content]]
Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.
You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.
For more on this, please refer to https://docs.gradle.org/8.2.1/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.
BUILD SUCCESSFUL in 9s
4 actionable tasks: 2 executed, 2 up-to-date
15:38:38: Execution finished 'test'.
```
