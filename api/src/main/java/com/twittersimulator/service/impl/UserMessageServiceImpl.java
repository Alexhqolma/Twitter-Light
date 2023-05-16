package com.twittersimulator.service.impl;

import com.twittersimulator.exception.TwitterUserException;
import com.twittersimulator.model.UserMessage;
import com.twittersimulator.repository.UserMessageRepository;
import com.twittersimulator.service.UserMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserMessageServiceImpl implements UserMessageService {
    private final UserMessageRepository userMessageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public UserMessageServiceImpl(UserMessageRepository userMessageRepository) {
        this.userMessageRepository = userMessageRepository;
    }

    @Override
    public UserMessage findById(Long id) {
        return userMessageRepository.findById(id)
                .orElseThrow(() -> new TwitterUserException("Can't find message by id: " + id));
    }

    @Override
    public UserMessage createMessage(UserMessage userMessage, MultipartFile file) {
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
            userMessage.setFileName(resultFileName);
            userMessage.setDateOfCreated(LocalDateTime.now());
        }
        return userMessageRepository.save(userMessage);
    }

    @Override
    public List<UserMessage> findAll() {
        return userMessageRepository.findAll();
    }
}
