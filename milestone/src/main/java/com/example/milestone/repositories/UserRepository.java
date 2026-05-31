package com.example.milestone.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.milestone.models.User;

/**
 * Provides access methods for User objects in the database.
 * 
 * The repository uses Spring Data JPA to retrieve user records
 * and validate if username or emails exist. 
 */
public interface UserRepository extends JpaRepository<User, UUID> {

	/**
	 * Retrieves a user by username.
	 * 
	 * @param username the username used to search for user
	 * @return user if found, empty if not
	 */
    Optional<User> findByUsername(String username);

    /**
     * Finds user by email address.
     * 
     * @param emailAddress email address used to search for user
     * @return user if found, empty if not
     */
    Optional<User> findByEmailAddress(String emailAddress);

    /**
     * Finds a user by username or email.
     * 
     * @param username the username used to search for
     * @param emailAddress the email to search for
     * @return user if found, empty if not
     */
    Optional<User> findByUsernameOrEmailAddress(String username, String emailAddress);

    /**
     * Checks if a username already exists in database.
     * 
     * @param username the username used to search the database
     * @return true if username exists, fails if not
     */
    boolean existsByUsername(String username);

    /**
     * Checks if email address exists in database.
     * 
     * @param emailAddress the email to search for
     * @return true if email is found, fail if not
     */
    boolean existsByEmailAddress(String emailAddress);
}
