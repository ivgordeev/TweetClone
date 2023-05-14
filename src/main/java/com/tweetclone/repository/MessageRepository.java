package com.tweetclone.repository;

import com.tweetclone.entity.Message;
import com.tweetclone.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ivan Gordeev 07.05.2023
 */

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Page<Message> findByTag(String tag, Pageable pageable);
    Page<Message> findAll(Pageable pageable);
    Page<Message> findAllByAuthor(User user, Pageable pageable);
}
