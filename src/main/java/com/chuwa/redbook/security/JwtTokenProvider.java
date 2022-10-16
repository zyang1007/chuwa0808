package com.chuwa.redbook.security;

import com.chuwa.redbook.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationsInMs;

    // generate token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationsInMs);

        String token = Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    // get username from the token
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    // validate JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;

        } catch (SignatureException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature!");
        } catch (MalformedJwtException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token!");
        } catch (ExpiredJwtException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token!");
        } catch (UnsupportedJwtException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token!");
        } catch (IllegalArgumentException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty!");
        }
    }

}
