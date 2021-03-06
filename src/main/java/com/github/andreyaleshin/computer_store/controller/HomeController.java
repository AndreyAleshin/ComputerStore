package com.github.andreyaleshin.computer_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
Контроллеры – это классы предназначенные для взаимодействия с клиентом (например web-страница)
Специальный тип класса, применяемый в MVC приложениях.
Похож на обычный сервлет HttpServlet, работающий с объектами HttpServletRequest и HttpServletResponse,
но с расширенными возможностями от Spring Framework.
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String showHome(Model model) {
        model.addAttribute("homeMessage", "This is a home page!");
        return "/home";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "/about";
    }

    @GetMapping("/wake-up")
    public String wakeUp() {
        return "/samurai";
    }

    @GetMapping("/error/403")
    public String accessDenied() {
        return "/error/403";
    }

}
