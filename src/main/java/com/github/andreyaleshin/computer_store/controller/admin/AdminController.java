package com.github.andreyaleshin.computer_store.controller.admin;

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

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-page")
    public String showAdminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findUserByUsername(auth.getName());
        model.addAttribute("username", "Welcome, " + user.get().getUsername() + "!");
        model.addAttribute(
                "adminMessage",
                "On this page you can manage users and products"
        );
        return "/admin/admin-page";
    }

}
