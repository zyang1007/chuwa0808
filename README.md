### springboot: redbook
- On this branch, add two features to the application: 1.Validation, 2.Exception handling.

**1. Validation** - check Request Body of controller layer, if not valid, returns customizied message.Steps:

1. Add new dependencies in pom.xml:
```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

2. **DTO layer**: add annotations & constraints to the fields/attributes of `PostDto` and `CommentDto`;

3. **Controller layer**: add `@Valid` annotation before every `@RequestBody` annotation - check if the input is valid(satisfy the constraints);


**2. Exception/error handling**: add new exception-handle-method into the `GlobalExceptionHandler` class. 
**Anotations:**
`@ExceptionHandler`: method Level; used to handle the specific exceptions and sending the custom responses to the client.

`@ControllerAdvice`: class level; to handle the exceptions globally.

**Steps:**
1. Create ErrorDetails Class;
2. Create GlobalExceptionHandler Class;
3. Test using Postman Client.

