### springboot: redbook
Implement basic Spring security:

1. Add new dependencies in pom.xml:
```
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

2. Add new configuration to `application.properties`:
```
# spring security
spring.security.user.name=chuwa
spring.security.user.password=chuwa
spring.security.user.roles=ADMIN
```

3. 
