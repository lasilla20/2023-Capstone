package com.example.project.Repository;

import com.example.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM user WHERE username = ?1
    User findByName(String name);  //최초 로그인 여부 체크
}