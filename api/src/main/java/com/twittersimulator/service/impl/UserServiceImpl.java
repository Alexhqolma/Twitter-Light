package com.twittersimulator.service.impl;

import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.User;
import com.twittersimulator.repository.UserRepository;
import com.twittersimulator.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByName(user.getName()) != null) {
            throw new TwitterUserException("User with name: "
                    + user.getName() + " already exist");
        }
        user.setRole("USER");
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TwitterUserException("Can't find User by id " + id));
    }
}
