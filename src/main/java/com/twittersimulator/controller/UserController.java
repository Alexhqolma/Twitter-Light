package com.twittersimulator.controller;

import com.twittersimulator.dto.mapper.UserMapper;
import com.twittersimulator.dto.request.UserRequestDto;
import com.twittersimulator.dto.response.UserResponseDto;
import com.twittersimulator.model.User;
import com.twittersimulator.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/registration")
    public String registration() {
        return "Registration Page";
    }


    @PostMapping("/registration")
    public UserResponseDto registration(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        return userMapper.toDto(userService.createUser(user));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
