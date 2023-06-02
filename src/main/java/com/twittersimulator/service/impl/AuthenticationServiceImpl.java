package com.twittersimulator.service.impl;

import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.User;
import com.twittersimulator.service.AuthenticationService;
import com.twittersimulator.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String userName, String password) throws TwitterUserException {
        Optional<User> userAccount = userService.findByName(userName);
        String encodedPassword = passwordEncoder.encode(password);
        if (userAccount.isEmpty() || userAccount.get().getPassword().equals(encodedPassword)) {
            throw new TwitterUserException("Incorrect username or password!!!");
        }
        return userAccount
                .orElseThrow(() ->
                        new RuntimeException("Can't find user with username " + userName));
    }
}
