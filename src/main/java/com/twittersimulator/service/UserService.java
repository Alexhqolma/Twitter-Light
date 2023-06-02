package com.twittersimulator.service;

import com.twittersimulator.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> findAll();

    User findById(Long id);

    Optional<User> findByName(String name);
}
