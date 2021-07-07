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
// write DOCS (not much)
// TESTS
// in pom.xml <version></version> разобраться
// в MainController, если залогинен и заходишь на страницу логина перенаправлять на др. страницу (с сообщением "Вы уже залогинены", предложить выйти???)
// change description in instructions.txt  in project root
// Serializable над Entity классами (o7planning.org)
// check all SonarLint warnings
/* попробовать без аннотаций валидироавть URL (в Product); если использовать только @URL, то при отправке пустой формы
на product-create и product-edit выдаёт ошибку (только если есть символы в поле - выдаёт ошибку "must be a valid URL")
 */
// add dateOfRegistration field of type LocalDate to User class
// Добавить ShoppingCart entity, связать таблицу shopping_carts и users (@OneToOne)
// implement SEARCH field
// "Cannot resolve..." in product-edit
// id сделать long (примитивом) AdminProductController и др.
// Кнопка EDIT обрезана в product-list
// Не отображается имя пользователя на 404.html

// add License (Apache 2.0, MIT???)