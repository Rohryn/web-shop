package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.User;
import org.springframework.data.repository.Repository;


public interface UserRepository extends Repository<User, Integer> {

    void addUser(User user);

    User getUser(String email);

    int getUsersCount();
}
