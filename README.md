### springboot: redbook

#### Actuator - a tool to monitor the application.

1. Add new dependencies in pom.xml:
```
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
2. Add new "configuration" to the `application.properties`:
```
# datasource
spring.datasource.url=jdbc:mysql://localhost:3306/redbook?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC

# acutator, JMX
management.endpoints.web.exposure.include=*
```

3. **Usage examples:**
- http://localhost:8080/actuator/health;
- .../actuator/beans;
- .../actuator/caches/...;
