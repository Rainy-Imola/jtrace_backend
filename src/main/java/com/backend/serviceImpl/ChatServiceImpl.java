package com.backend.serviceImpl;

import com.backend.dao.ChatDao;
import com.backend.entity.Chat;
import com.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;

    @Override
    public List<Chat> getChats(String from, String to) {
        return chatDao.getChats(from, to);
    }

    @Override
    public Chat addChat(Chat chat) {
        return chatDao.addChat(chat);
    }
}
