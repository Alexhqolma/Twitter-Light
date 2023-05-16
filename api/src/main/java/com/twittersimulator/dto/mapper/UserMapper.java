package com.twittersimulator.dto.mapper;

import com.twittersimulator.dto.request.UserRequestDto;
import com.twittersimulator.dto.response.UserResponseDto;
import com.twittersimulator.model.User;
import com.twittersimulator.model.UserMessage;
import com.twittersimulator.service.UserMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    private final UserMessageService userMessageService;

    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setMessages(dto.getMessageId()
                .stream()
                .map(userMessageService::findById)
                .collect(Collectors.toList()));
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setMessageId(user.getMessages()
                .stream()
                .map(UserMessage::getId)
                .collect(Collectors.toList()));
        userResponseDto.setRole(user.getRole());
        return userResponseDto;
    }
}
