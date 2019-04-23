package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    int getCount();

    User findByEmail(String email);
}
