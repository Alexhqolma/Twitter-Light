package com.twittersimulator.controller;

import com.twittersimulator.model.User;
import com.twittersimulator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/inject")
public class InjectController {
    private final UserService userService;

    @GetMapping
    public String inject() {
        /** Create test user */
        User user = new User();
        user.setName("admin");
        user.setPassword("adminadmin");
        userService.createUser(user);

        return "Injection success!";
    }

}
