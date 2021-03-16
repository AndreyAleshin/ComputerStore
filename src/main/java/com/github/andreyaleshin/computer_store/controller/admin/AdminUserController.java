package com.github.andreyaleshin.computer_store.controller.admin;

import com.github.andreyaleshin.computer_store.entity.User;
import com.github.andreyaleshin.computer_store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/user-list")
    public String showAllUsers(Model model) {
        model.addAttribute("userList", userService.findAllUsersByOrderByIdAsc());
        return "/admin/user-list";
    }

    @PostMapping("/admin/user-list/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        Optional<User> user = userService.findUserById(userId);

        if (user.isPresent()) {
            userService.deleteUserById(userId);
            log.debug(String.format("User with id %s successfully deleted.", user.get().getId()));
            return "redirect:/admin/user-list";
        } else {
            return "/error/404";
        }
    }

}
