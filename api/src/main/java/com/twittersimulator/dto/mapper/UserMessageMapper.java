package com.twittersimulator.dto.mapper;

import com.twittersimulator.dto.request.UserMessageRequestDto;
import com.twittersimulator.dto.response.UserMessageResponseDto;
import com.twittersimulator.model.UserMessage;
import com.twittersimulator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMessageMapper {
    private final UserService userService;

    public UserMessage toModel(UserMessageRequestDto dto) {
        UserMessage userMessage = new UserMessage();
        userMessage.setUser(userService.findById(dto.getUserId()));
        userMessage.setFileName(dto.getFileName());
        userMessage.setDateOfCreated(dto.getDateOfCreated());
        return userMessage;
    }

    public UserMessageResponseDto toDto(UserMessage userMessage) {
        UserMessageResponseDto dto = new UserMessageResponseDto();
        dto.setId(userMessage.getId());
        dto.setUserId(userMessage.getId());
        dto.setFileName(userMessage.getFileName());
        dto.setDateOfCreated(userMessage.getDateOfCreated());
        return dto;
    }
}
