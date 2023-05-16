package com.twittersimulator.controller;

import com.twittersimulator.dto.mapper.UserMessageMapper;
import com.twittersimulator.dto.request.UserMessageRequestDto;
import com.twittersimulator.dto.response.UserMessageResponseDto;
import com.twittersimulator.model.UserMessage;
import com.twittersimulator.service.UserMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UserMessageController {
    private final UserMessageMapper userMessageMapper;
    private final UserMessageService userMessageService;

    @PostMapping("/create")
    public UserMessageResponseDto createMessage(@RequestBody UserMessageRequestDto dto,
                                                @RequestParam("file") MultipartFile file) {
        UserMessage userMessage = userMessageMapper.toModel(dto);
        try {
            return userMessageMapper.toDto(userMessageService.createMessage(userMessage, file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<UserMessageResponseDto> getMessages() {
        return userMessageService.findAll()
                .stream()
                .map(userMessageMapper::toDto)
                .collect(Collectors.toList());
    }
}
