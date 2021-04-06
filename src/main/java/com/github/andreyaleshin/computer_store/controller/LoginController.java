package com.github.andreyaleshin.computer_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    // We don't define /login POST controller, it is provided by Spring Security
    @GetMapping("/login")
    public String login(Principal principal, Model model, String error) {
        model.addAttribute("title", "Login page");

        if (principal != null) {
            return "redirect:/home";
        }

        if (error != null) {
            model.addAttribute("loginErrorMessage", "Invalid username and/or password");
        }

        return "/login";
    }

}
