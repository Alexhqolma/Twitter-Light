package com.twittersimulator.service.impl;

import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.User;
import com.twittersimulator.repository.UserRepository;
import com.twittersimulator.service.UserService;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new TwitterUserException("User with name: "
                    + user.getName() + " already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
