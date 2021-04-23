package com.backend.daoImpl;

import com.backend.dao.MessageDao;
import com.backend.entity.Message;
import com.backend.entity.User;
import com.backend.repository.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getMessages() {
        return messageRepository.getMessages();
    }

    @Override
    public List<Message> findByAuthor(User author) {
        return messageRepository.findByAuthor(author);
    }

    @Override
    public Message releaseMessage(Message message) {
        return messageRepository.saveAndFlush(message);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageRepository.saveAndFlush(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message findById(Integer id) {
        if (messageRepository.findById(id).isPresent()) {
            return messageRepository.findById(id).get();
        } else {
            return null;
        }
    }
}
