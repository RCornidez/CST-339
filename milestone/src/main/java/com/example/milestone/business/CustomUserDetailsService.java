package com.example.milestone.business;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.milestone.repositories.UserRepository;

/**
 * Retrieves user details from the database for Spring Security.
 * 
 * This service lets Spring Security get a user by username/email
 * and converts it into a UserDetails object for login.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Creates a CustomUserDetailsService for accessing repository.
     * 
     * @param userRepository input is used to lookup specific user from database.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by username for authentication.
     * 
     * Converts user's record into a Spring Security UserDetails object. 
     * 
     * @param username username from login.
     * @return UserDetails object of authenticated user.
     * @throws UsernameNotFoundException if no matching username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.milestone.models.User user = userRepository.findByUsernameOrEmailAddress(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
