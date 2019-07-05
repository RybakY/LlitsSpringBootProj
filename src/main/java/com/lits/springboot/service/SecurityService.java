package com.lits.springboot.service;

public interface SecurityService {

    String findLoggedInUser();

    void autoLogin(String username, String password);
}
