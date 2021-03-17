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
        model.addAttribute("title", "Computer Store | Home");
        model.addAttribute("message", "This is a home page!");
        return "/home";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "/about";
    }

}
