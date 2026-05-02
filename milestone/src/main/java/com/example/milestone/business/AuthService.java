package com.example.milestone.business;

import com.example.milestone.models.User;
import com.example.milestone.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // LOGIN (checks database)
    public boolean authenticate(String username, String password) {

        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    // REGISTER (saves to database)
    public void register(User user) {
        userRepository.save(user);
    }
}