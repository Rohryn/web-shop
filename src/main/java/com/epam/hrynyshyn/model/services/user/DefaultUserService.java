package com.epam.hrynyshyn.model.services.user;

import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.user.UserRepository;
import com.epam.hrynyshyn.model.persistense.entity.User;

public class DefaultUserService implements UserService {
    private UserRepository repository;

    public DefaultUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUser(User user) {
        repository.addUser(user);
    }

    @Override
    public User getUser(String email) {
        return repository.getUser(email);
    }

    @Override
    public boolean emailReserved(String email) {
        return repository.getUser(email) != null;
    }

    @Override
    public boolean passwordCorrect(String email, String password) {
        return repository.getUser(email).getPassword().equals(password);
    }

    @Override
    public String getFullName(String email) {
        User user = repository.getUser(email);
        return String.format("%1s %2s", user.getFirstName(), user.getLastName());
    }

    @Override
    public int getUsersCount() {
        return repository.getUsersCount();
    }
}
