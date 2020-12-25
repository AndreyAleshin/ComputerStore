package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    Optional<User> findByUsername(String username);
}
