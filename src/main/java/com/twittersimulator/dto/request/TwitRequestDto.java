package com.twittersimulator.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class TwitRequestDto {
    private Long userId;
    private String fileName;
    private LocalDateTime dateOfCreated;
    private List<Long> likeIds;
}
