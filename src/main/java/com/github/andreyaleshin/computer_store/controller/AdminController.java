package com.github.andreyaleshin.computer_store.controller;

import com.github.andreyaleshin.computer_store.entity.User;
import com.github.andreyaleshin.computer_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminPage")
    public String showAdminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(auth.getName());
        model.addAttribute(
                "username",
                "Welcome "
                        + user.get().getUsername() + "/"
                        + user.get().getFirstName() + " "
                        + user.get().getLastName() + " ("
                        + user.get().getEmail() + ")"
        );
        model.addAttribute(
                "adminMessage",
                "Content Available Only for Users with ROLE_ADMIN"
        );
        return "/admin/adminPage";
    }

}
