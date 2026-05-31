package com.example.milestone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures the Spring Security.
 * 
 * Class defines public routes, routes that require authentication,
 * login page, logout, and the password encoder.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Configures the security filter chain.
	 * 
	 * Defines the login page, registration, error, and webjar resources as public. 
	 * Defines all other routes needing authentication. After login then application
	 * is redirected to product list. 
	 * @param http HttpSecurity object 
	 * @return SecurityFilterChain
	 * @throws Exception if error occurs while configuring security.
	 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/products/list", true)
                .failureUrl("/login?error")
                .permitAll())
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll());

        return http.build();
    }

    /**
     * Creates password encoder.
     * 
     * Encoder is used to hash and authenticate existing passwords.
     * @return Spring Security PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
