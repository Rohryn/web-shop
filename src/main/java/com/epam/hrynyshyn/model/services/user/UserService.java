package com.epam.hrynyshyn.model.services.user;

import com.epam.hrynyshyn.model.persistense.entity.User;


public interface UserService {

    void addUser(User user);

    User getUser(String email);

    boolean emailReserved(String email);

    boolean passwordCorrect(String email, String password);

    String getFullName(String email);

    int getUsersCount();
}
