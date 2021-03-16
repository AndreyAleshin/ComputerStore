package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    void deleteUserById(Long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    List<User> findAllUsersByOrderByIdAsc();

}
