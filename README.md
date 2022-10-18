### springboot: redbook

Integrating Swagger in Spring Boot App.

1. Add new dependency into **pom.xml**;
```
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-
swagger-ui -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>
```

2. Create a configuration class `SwaggerConfig` within the **config layer**.
- Configure Swagger Docket and UI.

3. Modify the configure() method of `SecurityDBJWTConfig` class.

4. Customizing Swagger REST Doc with Annotations:
- controller classes;
- DTO/payload classes. 

5. Check the built result:
- http://localhost:8080/swagger-ui/


#### Extra:
- change Spring Boot version to 2.4.2 since has some bugs on Spring Boot 2.7.0.
