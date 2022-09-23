### springboot: redbook
On this branch, add a "exception manager" `GlobalExceptionHandler` to handle different cases/exceptions; And, replace the mapToDto() and mapToEntity() methods with `ModelMapper` to do the data mapping work.


1. Add new dependencies in pom.xml:
```
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>2.4.5</version>
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
