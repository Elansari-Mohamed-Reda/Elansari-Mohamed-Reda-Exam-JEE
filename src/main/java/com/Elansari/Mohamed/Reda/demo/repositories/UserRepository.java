package com.Elansari.Mohamed.Reda.demo.repositories;

import com.Elansari.Mohamed.Reda.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}