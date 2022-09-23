### springboot: redbook
1. Add new dependencies in pom.xml:
```
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>2.4.5</version>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

2. Add new package **config** and `CommonConfig` class;

3. In service layer, use ModelMapper to replace mapToDto and mapToEntity:
- `PostServiceImpl`;
- `CommentServieImpl`;

4. Add more exceptions to **exception** package:
- com.chuwa.redbook.payload.ErrorDetails;
- com.chuwa.redbook.exception.GlobalExceptionHandler.



#### Extra:
Fixed the issue - comments are not automatically loaded when Get a Post (branch 04\_Comment).
