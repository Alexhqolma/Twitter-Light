package com.twittersimulator.service;

import com.twittersimulator.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> findAll();

    User findById(Long id);
}
