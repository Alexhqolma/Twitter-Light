package com.twittersimulator.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class UserRequestDto {
    private String name;
    private String password;
    private List<Long> messageId;
    private String role;
}
