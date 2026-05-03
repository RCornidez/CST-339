package com.example.milestone.business;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.milestone.models.User;
import com.example.milestone.repositories.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // =========================
    // LOGIN LOGIC (Business Layer)
    // =========================
    public boolean authenticate(String username, String password) {

        Optional<User> user = userRepository.findByUsernameOrEmailAddress(username, username);
        return user.map(foundUser -> foundUser.getPassword().equals(password)).orElse(false);
    }

    // =========================
    // REGISTRATION LOGIC
    // =========================
    public boolean register(String username, String password) {

        // EVA NOTE: placeholder registration logic
        // Normally this would save to a database

        System.out.println("User registered: " + username);

        return true;
    }
}
