package com.github.andreyaleshin.computer_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

// todo /user/** and /admin/**
/*
Контроллеры – это классы предназначенные для взаимодействия с клиентом (например web-страница)
Специальный тип класса, применяемый в MVC приложениях.
Похож на обычный сервлет HttpServlet, работающий с объектами HttpServletRequest и HttpServletResponse,
но с расширенными возможностями от Spring Framework.
 */
@Controller
public class MainController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("title", "Computer Store | Home");
        model.addAttribute("message", "This is home page!");
        return "/home";
    }

    // We don't define /login POST controller, it is provided by Spring Security
    @GetMapping("/login")
    public String login(Principal principal, Model model, String error) {
        model.addAttribute("title", "Login page");

        if (principal != null) {
            return "redirect:/home"; // todo если уже залогинен перенапрлять на какую-нибудь страницу
        }
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username and/or password");
        }

        return "/login";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/error/403")
    public String accessDenied() {
        return "/error/403";
    }

}
