package com.chuwa.redbook.controller;

import com.chuwa.redbook.DTO.security.JWTAuthResponseDTO;
import com.chuwa.redbook.DTO.security.LogInDTO;
import com.chuwa.redbook.DTO.security.SignUpDTO;
import com.chuwa.redbook.dao.security.RoleRepository;
import com.chuwa.redbook.dao.security.UserRepository;
import com.chuwa.redbook.entity.sercurity.Role;
import com.chuwa.redbook.entity.sercurity.User;
import com.chuwa.redbook.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/api/v1/auth/jwt")
public class AuthJWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthJWTController.class);

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LogInDTO logInDTO) {
        logger.info(logInDTO.getAccountOrEmail() + " is trying to sign in ...");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                logInDTO.getAccountOrEmail(), logInDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token from tokenProvider
        String token = tokenProvider.generateToken(authentication);
        JWTAuthResponseDTO jwtAuthResponseDTO = new JWTAuthResponseDTO(token);
        jwtAuthResponseDTO.setTokenType("JWT");

        logger.info(logInDTO.getAccountOrEmail() + " sign in successfully!");

        return ResponseEntity.ok(jwtAuthResponseDTO);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
        logger.info("New User is trying to sign up...");

        // verify new user info
        if (userRepository.existsByAccount(signUpDTO.getAccount())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDTO.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setAccount(signUpDTO.getAccount());
        user.setPassword(signUpDTO.getPassword());

        Role roles = null;
        if (signUpDTO.getAccount().contains("admin")){
            roles = roleRepository.findByName("ROLE_ADMIN").get();
        } else {
            roles = roleRepository.findByName("ROLE_USER").get();
        }

        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);

        logger.info("User registered successfully!");

        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

}
