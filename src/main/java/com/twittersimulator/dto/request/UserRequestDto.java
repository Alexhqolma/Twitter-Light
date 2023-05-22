package com.twittersimulator.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;
    private String password;
    private List<Long> twitIds;
    private String role;
}
