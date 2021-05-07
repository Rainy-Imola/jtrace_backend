package com.backend.serviceImpl;

import com.backend.dao.ChatDao;
import com.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;
}
