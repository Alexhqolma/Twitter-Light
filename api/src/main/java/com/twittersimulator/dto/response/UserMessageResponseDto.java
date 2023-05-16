package com.twittersimulator.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserMessageResponseDto {
    private Long id;
    private Long userId;
    private String fileName;
    private LocalDateTime dateOfCreated;
}
