package com.twittersimulator.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String password;
    private List<Long> messageId;
    private String role;
}
