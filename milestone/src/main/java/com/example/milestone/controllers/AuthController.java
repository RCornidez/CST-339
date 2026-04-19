package com.example.milestone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {

        // Temporary username and password for this milestone
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/";
        }

        model.addAttribute("errorMessage", "Invalid username or password.");
        return "pages/login";
    }
}
