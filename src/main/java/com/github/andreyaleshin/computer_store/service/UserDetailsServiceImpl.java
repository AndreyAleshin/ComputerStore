package com.github.andreyaleshin.computer_store.service;

import com.github.andreyaleshin.computer_store.entity.User;
import com.github.andreyaleshin.computer_store.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// Это то, как мы получаем пользователей из БД
// Указывает, что класс является сервисом для реализации бизнес логики
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Метод вызывается при отправке логин формы
    @Override
    @Transactional(readOnly = true) // Всё, что находится в этом методе необходимо выполнить внутри одной транзакции
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " was not found in the database")
                );

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        log.debug(String.format("User with name : %s and password: %s was created.",
                user.getUsername(), user.getPassword()));

        // Возвращаем объект внутреннего Spring User
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }

}