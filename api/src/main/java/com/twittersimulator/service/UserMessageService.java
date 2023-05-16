package com.twittersimulator.service;

import com.twittersimulator.model.UserMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserMessageService {
    UserMessage findById(Long id);

    UserMessage createMessage(UserMessage userMessage, MultipartFile file) throws IOException;

    List<UserMessage> findAll();
}
