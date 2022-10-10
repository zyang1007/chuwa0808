### springboot: redbook
Implement basic Spring security:

1. Add new dependencies in pom.xml:
```
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- Spring Security is by default turned on for all the URLs in the application (e.g. http://localhost:8080/api/v1/posts).
- Default username and password: UserId = user, Password = is generated in the console when run the application.

2. Define userId and password - add configuration to `application.properties`:
```
# spring security
spring.security.user.name=chuwa
spring.security.user.password=chuwa
spring.security.user.roles=ADMIN
```
