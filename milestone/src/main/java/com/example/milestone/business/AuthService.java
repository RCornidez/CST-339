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

    // REGISTER (saves to database)
    public void register(User user) {
        userRepository.save(user);
    }
}
