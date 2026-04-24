package com.example.milestone.business;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // =========================
    // LOGIN LOGIC (Business Layer)
    // =========================
    public boolean authenticate(String username, String password) {

        // EVA NOTE: temporary hardcoded logic (no database yet)
        // In real apps, this would check a database

        if ("admin".equals(username) && "password".equals(password)) {
            return true;
        }

        return false;
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