package com.qweed.qweed.logic;

import com.qweed.qweed.interfaces.IUserRepository;
import com.qweed.qweed.interfaces.collections.IUserCollection;
import com.qweed.qweed.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCollection implements IUserCollection {

    private static IUserRepository userRepository;

    public UserCollection(IUserRepository repository) {
        userRepository = repository;
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        List<User> users = userRepository.findAll();

        User user = users.stream().findAny().filter(foundUser -> foundUser.getUsername().equals(username) && foundUser.getPassword().equals(password)).get();

        return user;
    }
}

