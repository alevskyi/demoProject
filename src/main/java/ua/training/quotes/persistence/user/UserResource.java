package ua.training.quotes.persistence.user;

import ua.training.quotes.model.User;

import java.util.Set;

public interface UserResource {

    void addUser(User user);

    User getUser(String username);

    Set<User> getAllUsers();
}
