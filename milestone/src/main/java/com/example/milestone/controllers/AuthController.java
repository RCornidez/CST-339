package com.example.milestone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.milestone.business.AuthService;
import com.example.milestone.models.User;

import jakarta.validation.Valid;

/**
 * Handles login and registration requests.
 * 
 * This controller displays the registration and login pages. 
 * When new registration requests are made this processes them.
 */
@Controller
public class AuthController {

    private final AuthService authService;

    /**
     * Creates an AuthController with correct authentication service.
     * 
     * @param authService service used to handle registration.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Displays login page.
     * @return login view.
     */
    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    /**
     * Initializes empty user object and displays registration page.
     * @param model used to pass data to registration view
     * @return registration view
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "pages/register";
    }

    /**
     * Processes registration form after submit.
     * 
     * User is registered and redirected to login page if no errors occur.
     * 
     * @param user the user submitted registration form
     * @param bindingResult validation results from submitted user
     * @param model model used to pass error messages
     * @return registration view if errors, or login view if successful
     */
    @PostMapping("/register")
    public String doRegister(@Valid User user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "pages/register";
        }

        try {
            authService.register(user);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("registrationError", ex.getMessage());
            return "pages/register";
        }

        return "redirect:/login?registered";
    }
}
