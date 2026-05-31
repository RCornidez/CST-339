package com.example.milestone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot entry point for application.
 * 
 * Tells Spring Boot to enable auto-configuration and scanning
 * once application runs.
 */
@SpringBootApplication
public class MilestoneApplication {

	/**
	 * Starts application.
	 * 
	 * @param args command line arguments 
	 */
	public static void main(String[] args) {
		SpringApplication.run(MilestoneApplication.class, args);
	}

}
