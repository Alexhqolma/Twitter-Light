package com.twittersimulator.repository;

import com.twittersimulator.model.Twit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitRepository extends JpaRepository<Twit, Long> {
    Page<Twit> findByUser_Id(Long id, Pageable pageable);
}
