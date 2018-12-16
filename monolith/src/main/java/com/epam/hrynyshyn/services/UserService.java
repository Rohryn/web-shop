package com.epam.hrynyshyn.services;

import com.epam.hrynyshyn.model.entity.User;


public interface UserService {

    void add(User user);

    User get(String email);

    boolean isEmailReserved(String email);

    boolean isPasswordCorrect(String email, String password);

    String getFullName(String email);

    int getCount();
}
