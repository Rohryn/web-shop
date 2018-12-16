package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.user;

import com.epam.hrynyshyn.model.persistense.entity.User;


public interface UserRepository {

    void addUser(User user);

    User getUser(String email);

    int getUsersCount();
}
