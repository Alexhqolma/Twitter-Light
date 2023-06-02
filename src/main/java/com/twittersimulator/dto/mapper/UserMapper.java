package com.twittersimulator.dto.mapper;

import com.twittersimulator.dto.request.UserRequestDto;
import com.twittersimulator.dto.response.UserResponseDto;
import com.twittersimulator.model.Twit;
import com.twittersimulator.model.User;
import com.twittersimulator.service.TwitService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final TwitService twitService;

    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setTwits(dto.getTwitIds()
                .stream()
                .map(twitService::findById)
                .collect(Collectors.toList()));
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setTwitIds(user.getTwits()
                .stream()
                .map(Twit::getId)
                .collect(Collectors.toList()));
        userResponseDto.setRole(user.getRole());
        return userResponseDto;
    }
}
