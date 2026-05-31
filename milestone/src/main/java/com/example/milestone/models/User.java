package com.example.milestone.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model representing a user object stored in the database.
 * 
 * This class maps to the table of users which stores the first name,
 * last name, email, phone number, username, and password of a user.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message="First name is a required field")
	@Size(min=2, max=30, message="First name must be between 2 and 30 characters")
    @Column(name = "first_name")
	private String firstName;
	
	@NotNull(message="Last name is a required field")
	@Size(min=2, max=30, message="Last name must be between 2 and 30 characters")
    @Column(name = "last_name")
	private String lastName;
	
	@NotNull(message="Email address is a required field")
	@Size(min=12, max=40, message="Email address must be between 12 and 40 characters")
    @Column(name = "email")
	private String emailAddress;
	
	@NotNull(message="Phone number is a required field")
	@Size(min=10, max=20, message="Phone number must be between 10 and 20 characters")
    @Column(name = "phone_number")
	private String phoneNumber;
	
	@NotNull(message="User name is a required field")
	@Size(min=4, max=40, message="User name must be between 4 and 40 characters")
    @Column(name = "username")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=8, max=255, message="Password must be between 8 and 255 characters")
    @Column(name = "password")
	private String password;
	
	/**
	 * Default constructor
	 */
	public User()
	{
		
	}
	
	/**
	 * Initializes user with all fields.
	 * 
	 * @param firstName first name of user
	 * @param lastName the last name of a user
	 * @param emailAddress the email of a user
	 * @param phoneNumber the phone number of a user
	 * @param username the username of a user
	 * @param password the password of a user
	 */
	public User(String firstName, String lastName, String emailAddress, String phoneNumber, String username,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
	}
	/**
	 * Returns the user's id
	 * 
	 * @return id the user id
	 */
    public UUID getId() {
        return id;
    }
    /**
     * Sets the user's id
     * 
     * @param id the user id
     */
    public void setId(UUID id) {
        this.id = id;
    }
    /**
     * Returns username of a user.
     * 
     * @return username the username of a user
     */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets the username for a user.
	 * 
	 * @param username the user's username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Returns the user's password.
	 * 
	 * @return password the password of a user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the user's password.
	 * 
	 * @param password the password of a user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Returns the first name of a user.
	 * 
	 * @return firstName the first name of a user
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Sets the first name of a user.
	 * 
	 * @param firstName is the firstname of a user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Returns the last name of a user.
	 * 
	 * @return lastName the last name of a user
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets the last name of a user.
	 * 
	 * @param lastName is the last name of a user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Returns email address of user.
	 * 
	 * @return emailAddress is email of a user
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * Sets the email address for a user.
	 * 
	 * @param emailAddress the email of a user
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * Returns phone number of a user.
	 * 
	 * @return phoneNumber is the phone number of a user
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Sets the phone number of a user.
	 * 
	 * @param phoneNumber the phone number of a user
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
