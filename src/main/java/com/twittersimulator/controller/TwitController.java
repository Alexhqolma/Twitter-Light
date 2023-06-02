package com.twittersimulator.controller;

import com.twittersimulator.dto.mapper.TwitMapper;
import com.twittersimulator.dto.request.TwitRequestDto;
import com.twittersimulator.dto.response.TwitResponseDto;
import com.twittersimulator.model.Twit;
import com.twittersimulator.service.TwitService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/twits")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class TwitController {
    private final TwitMapper twitMapper;
    private final TwitService twitService;

    @PostMapping("/create")
    public TwitResponseDto createTwit(@RequestBody TwitRequestDto dto,
                                         @RequestParam("file") MultipartFile file) {
        Twit twit = twitMapper.toModel(dto);
        try {
            return twitMapper.toDto(twitService.createTwit(twit, file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<TwitResponseDto> getTwits() {
        return twitService.findAll()
                .stream()
                .map(twitMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public List<TwitResponseDto> getUserTwits(@PathVariable Long id,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return twitService.findByUser_Id(id, page, size)
                .stream()
                .map(twitMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{twitId}/{userId}")
    public TwitResponseDto manageLike(@PathVariable Long twitId, @PathVariable Long userId) {
        return twitMapper.toDto(twitService.manageTwit(twitId, userId));
    }
}
