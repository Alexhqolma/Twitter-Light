package com.twittersimulator.controller;

import com.twittersimulator.dto.response.UserResponseDto;
import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.User;
import com.twittersimulator.security.jwt.JwtTokenProvider;
import com.twittersimulator.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserResponseDto userResponseDto)
            throws TwitterUserException {
        User userAccount = authenticationService.login(userResponseDto.getName(),
                userResponseDto.getPassword());
        String token = jwtTokenProvider.createToken(userAccount.getName(), userAccount.getRole());
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
