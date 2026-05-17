package com.example.milestone.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.milestone.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAddress(String emailAddress);

    Optional<User> findByUsernameOrEmailAddress(String username, String emailAddress);

    boolean existsByUsername(String username);

    boolean existsByEmailAddress(String emailAddress);
}
