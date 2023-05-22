package com.twittersimulator.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class TwitResponseDto {
    private Long id;
    private Long userId;
    private String fileName;
    private LocalDateTime dateOfCreated;
    private List<Long> likeIds;
}
