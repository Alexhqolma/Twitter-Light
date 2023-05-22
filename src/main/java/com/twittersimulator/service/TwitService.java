package com.twittersimulator.service;

import com.twittersimulator.model.Twit;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TwitService {
    Twit findById(Long id);

    Twit createTwit(Twit twit, MultipartFile file) throws IOException;

    List<Twit> findAll();

    List<Twit> findByUser_Id(Long id, int page, int size);

    Twit manageTwit(Long twitId, Long userId);
}
