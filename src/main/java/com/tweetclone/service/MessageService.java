package com.tweetclone.service;

import com.tweetclone.entity.Message;
import com.tweetclone.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Gordeev 14.05.2023
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Page<Message> messageList(Pageable pageable, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepository.findByTag(filter, pageable);
        } else {
            return messageRepository.findAll(pageable);
        }
    }
}
