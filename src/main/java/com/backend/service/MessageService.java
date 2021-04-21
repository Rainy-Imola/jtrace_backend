package com.backend.service;

import com.backend.entity.Message;
import com.backend.entity.User;

import java.util.List;

public interface MessageService {
    List<Message> getMessages();
    List<Message> findByAuthor(User author);
    Message releaseMessage(Message message);
    Message updateMessage(Message message);
    void deleteMessage(Integer id);
    Message findById(Integer id);
}
