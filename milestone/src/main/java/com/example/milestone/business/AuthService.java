package com.example.milestone.business;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.milestone.models.User;
import com.example.milestone.repositories.UserRepository;

/**
 * Provides the business logic for user registration.
 * 
 * This class checks for a unique username and password. 
 * Encodes password and saves it to userRepository.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Instantiates an AuthService with right repository and password
     * encoder.
     * 
     * @param userRepository the repository to access user data
     * @param passwordEncoder the encoder to hash passwords
     */
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     * 
     * Method checks for duplicate username and email then
     * encodes the password and stores user in database.
     * @param user the user that is registered
     * @throws IllegalArgumentException if username or email is a duplicate.
     */
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
            throw new IllegalArgumentException("Email address already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
