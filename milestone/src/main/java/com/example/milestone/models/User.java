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
	@Size(min=8, max=40, message="Password must be between 8 and 40 characters")
    @Column(name = "password")
	private String password;
	
	public User()
	{
		
	}
	
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
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
