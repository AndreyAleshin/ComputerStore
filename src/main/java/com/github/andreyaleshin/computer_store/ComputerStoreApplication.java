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

// todo попробовать сдлать gif-ку вебсайта в проекте (README.md) на Github
// todo write DOCS
// todo in pom.xml <version></version> разобраться
// todo Docker разобраться
// todo add favicon
// todo https://www.jhipster.tech/
// todo in project structure "Module ComputerStore: invalid item 'jquery' in the dependencies list"

// todo change YAML, SQL color scheme
// todo change JavaScript color scheme

// todo в MainController, если залогинен и заходишь на страницу логина перенаправлять на др. страницу (с сообщением "Вы уже залогинены", предложить выйти???)
// todo change description in initDB.txt  in project root
// todo add dateOfRegistration field of type LocalDate

// todo Serializable над Entity классами
// todo видео Geekbrains Spring AOP.mp4 поменять Role (ManyToMany), UserService, UserDetailsServiceImpl (что-то сделать с этим)
// todo UserService(interface) extends UserDetailsService -> UserServiceImpl implements UserService
// todo add RoleRepository
// todo у админа сделать ROLE_ADMIN и ROLE_USER

//todo попробовать сделать develop branch (от master)