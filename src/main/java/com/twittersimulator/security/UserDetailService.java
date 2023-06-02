package com.twittersimulator.security;

import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.User;
import com.twittersimulator.model.UserDetail;
import com.twittersimulator.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        return user.map(UserDetail::new)
                .orElseThrow(() ->
                        new TwitterUserException("Can't find user by username " + username));
    }
}
