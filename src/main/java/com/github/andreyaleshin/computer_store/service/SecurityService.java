package com.github.andreyaleshin.computer_store.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
