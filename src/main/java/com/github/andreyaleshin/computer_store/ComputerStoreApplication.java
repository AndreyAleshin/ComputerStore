package com.github.andreyaleshin.computer_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
    }

}

// todo issues below
// попробовать сдлать gif-ку вебсайта в проекте (README.md) на Github
// write DOCS (not much)
// in pom.xml <version></version> разобраться
// Docker???
// add favicon <link rel="icon" href="/favicon.ico">
// change JavaScript, YAML, SQL color scheme
// в MainController, если залогинен и заходишь на страницу логина перенаправлять на др. страницу (с сообщением "Вы уже залогинены", предложить выйти???)
// change description in initDB.txt  in project root
// add dateOfRegistration field of type LocalDate
// Serializable над Entity классами
// UserService(interface) extends UserDetailsService -> UserServiceImpl implements UserService
// у админа сделать ROLE_ADMIN и ROLE_USER???
// check all SonarLint warnings
// если залогинился как admin перенаправлять на /admin/adminPage; user -> /user/info
// не работает шрифт на некоторых страницах (например, /user/info) -> дело в main.css
// если нет пользователей или продуктов в БД выводить на страницу текст об отсутствии <h2 th:case="null">No users yet!</h2>???
// "Cannot resolve..." homeMessage in product-edit
/* попробовать без аннотаций валидироавть URL (в Product); если использовать только @URL, то при отправке пустой формы
на product-create и product-edit выдаёт ошибку (только если есть символы в поле - выдаёт ошибку "must be a valid URL")
 */
// убрать поле Status из User класса???
// implement logout page???
// Добавить ShoppingCart entity, связать таблицу shopping_carts и users (@OneToOne)
// При добавлении товара в корзину выводить где-нибудь уведомление о добавлении
// выровнять footer по левому краю
// добавить значок валюты возле стоимости