package com.twittersimulator.service.impl;

import com.twittersimulator.dto.mapper.TwitMapper;
import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.Twit;
import com.twittersimulator.model.User;
import com.twittersimulator.repository.TwitRepository;
import com.twittersimulator.repository.UserRepository;
import com.twittersimulator.service.TwitService;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TwitServiceImpl implements TwitService {
    private final TwitRepository twitRepository;
    private final TwitMapper twitMapper;
    private final UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public TwitServiceImpl(TwitRepository twitRepository,
                           TwitMapper userMessageMapper,
                           UserRepository userRepository) {
        this.twitRepository = twitRepository;
        this.twitMapper = userMessageMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Twit findById(Long id) {
        return twitRepository.findById(id)
                .orElseThrow(() -> new TwitterUserException("Can't find twit by id: " + id));
    }

    @Override
    public Twit createTwit(Twit twit, MultipartFile file) {
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            twit.setFileName(resultFileName);
            twit.setDateOfCreated(LocalDateTime.now());
        }
        return twitRepository.save(twit);
    }

    @Override
    public List<Twit> findAll() {
        return twitRepository.findAll();
    }

    @Override
    public List<Twit> findByUser_Id(Long id, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Twit> list = twitRepository.findByUser_Id(id, pageRequest);
        return list.stream().map(twitMapper::toDto).toList()
                .stream().map(twitMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Twit manageTwit(Long twitId, Long userId) {
        Twit twit = twitRepository.findById(twitId)
                .orElseThrow(() -> new TwitterUserException("Can't find twit by id: " + twitId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TwitterUserException("Can't find user by id: " + userId));;
        List<User> likes = twit.getLikes();
        if (likes.contains(user)) {
            likes.remove(user);
        } else {
            likes.add(user);
        }
        twit.setLikes(likes);
        return twitRepository.save(twit);
    }
}
