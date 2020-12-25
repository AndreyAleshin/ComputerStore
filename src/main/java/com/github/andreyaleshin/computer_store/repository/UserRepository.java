package com.github.andreyaleshin.computer_store.repository;

import com.github.andreyaleshin.computer_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
Указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
Аннотация может использоваться для реализации шаблона DAO.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//    User findByUsername(String username);
}
