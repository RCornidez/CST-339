package com.example.milestone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.milestone.models.User;

import jakarta.validation.Valid;


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

		return "redirect:/login";
	}
}
