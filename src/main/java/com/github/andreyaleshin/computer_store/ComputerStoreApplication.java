package com.github.andreyaleshin.computer_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://localhost:8080/
@SpringBootApplication
public class ComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
    }

}

// todo issues below
// 1) попробовать сдлать gif-ку вебсайта в проекте (README.md) на Github
// 2) write DOCS
// 3) in pom.xml <version></version> разобраться
// 4) Docker разобраться
// 5) add favicon
// 6) change YAML, SQL color scheme
// 7) change JavaScript color scheme
// 8) в MainController, если залогинен и заходишь на страницу логина перенаправлять на др. страницу (с сообщением "Вы уже залогинены", предложить выйти???)
// 9) change description in initDB.txt  in project root
// 10) add dateOfRegistration field of type LocalDate
// 11) Serializable над Entity классами
// 12) видео Geekbrains Spring AOP.mp4 поменять Role (ManyToMany), UserService, UserDetailsServiceImpl (что-то сделать с этим)
// 13) UserService(interface) extends UserDetailsService -> UserServiceImpl implements UserService
// 14) у админа сделать ROLE_ADMIN и ROLE_USER
// 15) check all SonarLint warnings