package org.example.controllers;

import org.example.documentmanagerdao.UserDao;
import org.example.documentmanagerservices.AuthService;

public class AuthController {
    UserDao userDao;
    AuthService authService;

    //won't be void
    public void login(String username, String password) {
        authService.performLogin();
    }
    //won't be void
    public void logout() {
        authService.performLogout();
    }
}
