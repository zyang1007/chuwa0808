### springboot: redbook
Implementing Spring security - Json Web Token(JWT).

1. Adding JWT dependency;

2. Create `JwtAuthenticationEntryPoint` within **security layer**.

3. Add JtwProperties in `application.properties`.

4. Create `JwtTokenProvider` within **security layer**.

5. `JwtAuthenticationFilter` within **security layer**.

6. Create `JWTAuthResponseDTO`.

7. Configure JWT in Spring Security Configuration.

8. Change logIn/signIn API to return token to client.

## New Terms and definitions:
**Authentication** - to validate the username and password.

**Authorization** - to varify what resource a user has access to.
