package com.example.milestone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.milestone.business.AuthService;
import com.example.milestone.models.User;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "pages/register";
    }

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
