### springboot: redbook
Implement Spring security - in memory.

1. Comment out the defined role in `application.properities`;
2. Add spring security properties into `config` and `controller` layers.
- add `SecurityInmemoryConfig` class in `config` layer, and creates two in-memory roles with the class method;
- add "security policy" to the getAllPosts() mehtod of controller class, e.g.: 
```
@PreAuthorize("hasRole('ADMIN')")
@GetMapping
    public PostResponse getAllPosts(){
	...
    }
```

#### Note:
- user "chuwa" has role USER; user "admin" has role ADMIN;
- so admin can call the api, chuwa can not.

