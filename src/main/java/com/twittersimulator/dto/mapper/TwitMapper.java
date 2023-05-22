package com.twittersimulator.dto.mapper;

import com.twittersimulator.dto.request.TwitRequestDto;
import com.twittersimulator.dto.response.TwitResponseDto;
import com.twittersimulator.model.Twit;
import com.twittersimulator.model.User;
import com.twittersimulator.service.UserService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TwitMapper {
    private final UserService userService;

    public Twit toModel(TwitRequestDto dto) {
        Twit twit = new Twit();
        twit.setUser(userService.findById(dto.getUserId()));
        twit.setFileName(dto.getFileName());
        twit.setDateOfCreated(dto.getDateOfCreated());
        twit.setLikes(dto.getLikeIds()
                .stream()
                .map(userService::findById)
                .collect(Collectors.toList()));
        return twit;
    }

    public Twit toModel(TwitResponseDto dto) {
        Twit twit = new Twit();
        twit.setUser(userService.findById(dto.getUserId()));
        twit.setFileName(dto.getFileName());
        twit.setDateOfCreated(dto.getDateOfCreated());
        twit.setLikes(dto.getLikeIds()
                .stream()
                .map(userService::findById)
                .collect(Collectors.toList()));
        return twit;
    }

    public TwitResponseDto toDto(Twit twit) {
        TwitResponseDto dto = new TwitResponseDto();
        dto.setId(twit.getId());
        dto.setUserId(twit.getId());
        dto.setFileName(twit.getFileName());
        dto.setDateOfCreated(twit.getDateOfCreated());
        dto.setLikeIds(twit.getLikes()
                .stream()
                .map(User::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
