package com.chuwa.redbook.dao.security;

import com.chuwa.redbook.entity.sercurity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAccount(String account);
    Optional<User> findByAccountOrEmail(String account, String email);

    Boolean existsByAccount(String account);
    Boolean existsByEmail(String email);
}
