package com.twittersimulator.service;

import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.User;

public interface AuthenticationService {
    User login(String name, String password) throws TwitterUserException;
}
