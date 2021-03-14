package com.github.andreyaleshin.computer_store.controller.admin;

import com.github.andreyaleshin.computer_store.service.UserService;
import com.github.andreyaleshin.computer_store.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AdminUserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/admin/user-list")
    public String showAllUsers(Model model) {
        model.addAttribute("userList", userService.findAllUsersByOrderByIdAsc());
        return "/admin/user-list";
    }

}
