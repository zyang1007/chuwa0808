### springboot: redbook
On this branch, add Validation features to the application:

**Validation** - check Request Body of controller layer, if not valid, returns customizied message.Steps:

- Add new dependencies in pom.xml:
```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

- **DTO layer**: add annotations & constraints to the fields/attributes of `PostDto` and `CommentDto`;

- **Controller layer**: add `@Valid` annotation before every `@RequestBody` annotation - check if the input is valid(satisfy the constraints);

- add new exception-handle-method into the `GlobalExceptionHandler` class.



**Conclusion:**
- apply rules/constraints on the Object's attributes in payload(DTO) layer;
- check if Request Body is valid with @Valid in controller layer, throw exception if invalid;
- (global)exception handler receives and handle exceptions.
