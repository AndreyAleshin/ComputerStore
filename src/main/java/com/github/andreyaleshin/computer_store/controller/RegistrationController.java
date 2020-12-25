package com.github.andreyaleshin.computer_store.controller;

import com.github.andreyaleshin.computer_store.entity.User;
import com.github.andreyaleshin.computer_store.service.SecurityService;
import com.github.andreyaleshin.computer_store.service.UserService;
import com.github.andreyaleshin.computer_store.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public RegistrationController(UserService userService,
                                  SecurityService securityService,
                                  UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }


    @GetMapping
    public String register(Model model) {
        User user = new User();
        model.addAttribute("userForm", user);
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        String notHashedPassword = userForm.getPassword();
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.saveUser(userForm);
        securityService.autoLogin(userForm.getUsername(), notHashedPassword);

        return "redirect:/";
    }

}
