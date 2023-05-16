package com.twittersimulator.controller;

import com.twittersimulator.model.User;
import com.twittersimulator.model.UserMessage;
import com.twittersimulator.service.UserMessageService;
import com.twittersimulator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class InjectController {
    private final UserService userService;
    private final UserMessageService userMessageService;

    @GetMapping("/inject")
    public String contentInjection() {
        /** Creation of first User */
        User testUser = new User();
        testUser.setName("Nikolay");
        testUser.setPassword("123");
        userService.createUser(testUser);

        UserMessage userMessage = new UserMessage();
        userMessage.setDateOfCreated(LocalDateTime.now());
        userMessage.setUser(testUser);

        return "Done";
    }
}
