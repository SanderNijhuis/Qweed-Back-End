package com.qweed.interfaces.collections;

import com.qweed.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserCollection {

    void deleteUser(long id);
    User save(User user);
    List<User> getAllUsers();
    Optional<User> getUser(long id);
    User login(String email, String password);
}
