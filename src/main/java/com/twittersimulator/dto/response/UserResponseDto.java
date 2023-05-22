package com.twittersimulator.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private List<Long> twitIds;
    private String role;
}
