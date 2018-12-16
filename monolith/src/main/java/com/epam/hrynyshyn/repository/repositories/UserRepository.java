package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.User;


public interface UserRepository {

    void addUser(User user);

    User getUser(String email);

    int getUsersCount();
}
