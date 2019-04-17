package com.epam.hrynyshyn.services.impl;

import com.epam.hrynyshyn.repository.repositories.UserRepository;
import com.epam.hrynyshyn.model.entity.User;
import com.epam.hrynyshyn.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(User user) {
        repository.addUser(user);
    }

    @Override
    public User get(String email) {
        return repository.getUser(email);
    }

    @Override
    public boolean isEmailReserved(String email) {
        return repository.getUser(email) != null;
    }

    @Override
    public boolean isPasswordCorrect(String email, String password) {
        return repository.getUser(email).getPassword().equals(password);
    }

    @Override
    public String getFullName(String email) {
        User user = repository.getUser(email);
        return String.format("%1s %2s", user.getFirstName(), user.getLastName());
    }

    @Override
    public int getCount() {
        return repository.getUsersCount();
    }
}
