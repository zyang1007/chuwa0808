### springboot: redbook
Implement Spring security - storing user info in database.

1. Disable the user-role configred in `application.properties`;

2. Adding `User` and `Role` classes into **entity layer**:
- Relationship between User and Role: **many-to-many**.

3. Adding `UserRepository` and `RoleRepository` classes into **repository layer**.
- Define needed methods within these classes using JPA naming convention.

4. Adding below classes into `service layer`:
- `CusomUserDetailsService` implements `UserDetailsService`;
- Autowiring **UserRepository**.

5. Adding `LogInDTO` and `SignUpDTO` class to the **DTO / payload layer**.

6. Adding a Configuration class into **config layer**:
- `SecurityDBConfig` extends `WebSecurityConfigurerAdapter`.
- Autowiring **customUserDetailsService**;
- Beans: **PasswordEncoder, AuthenticationManager**.

7. Adding `AuthController` into the **controller layer**.
- Autowiring **AuthenticationManager, UserRepository, RoleRepository, PasswordEncoder**.
- Define two more PostMapping functions as well as their corresponding path(url).
