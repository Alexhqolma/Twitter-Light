package com.twittersimulator.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserMessageRequestDto {
    private Long userId;
    private String fileName;
    private LocalDateTime dateOfCreated;
}
