package com.chuwa.redbook.controller;

import com.chuwa.redbook.DTO.security.LogInDTO;
import com.chuwa.redbook.DTO.security.SignUpDTO;
import com.chuwa.redbook.dao.security.RoleRepository;
import com.chuwa.redbook.dao.security.UserRepository;
import com.chuwa.redbook.entity.sercurity.Role;
import com.chuwa.redbook.entity.sercurity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity<String> authenticateUser(@RequestBody LogInDTO logInDTO) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                logInDTO.getAccountOrEmail(), logInDTO.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User sign-in successfully!", HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
        // check if username(account or email) already exists in DB
        if (userRepository.existsByAccount(signUpDTO.getAccount())){
            return new ResponseEntity<>("User account is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDTO.getEmail())){
            return new ResponseEntity<>("User email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create a new user
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setAccount(signUpDTO.getAccount());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user); // save user info to DB

        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }
}
