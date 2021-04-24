package com.backend.serviceImpl;

import com.backend.dao.MessageDao;
import com.backend.entity.Message;
import com.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public List<Message> getMessages() {
        return messageDao.getMessages();
    }

    @Override
    public List<Message> findByAuthor(Integer author) {
        return messageDao.findByAuthor(author);
    }

    @Override
    public Message releaseMessage(Message message) {
        return messageDao.releaseMessage(message);
    }

    @Override
    public Message updateMessage(Message message) { return messageDao.updateMessage(message); }

    @Override
    public void deleteMessage(Integer id) { messageDao.deleteMessage(id); }

    @Override
    public Message findById(Integer id) {
        return messageDao.findById(id);
    }

}
