package com.backend.dao;

import com.backend.entity.Message;

import java.util.List;

public interface MessageDao {
    List<Message> getMessages();
    List<Message> findByAuthor(Integer author);
    Message releaseMessage(Message message);
    Message updateMessage(Message message);
    void deleteMessage(Integer id);
    Message findById(Integer id);
    Message likeMessage(Integer id);
    Message unlikeMessage(Integer id);
}
