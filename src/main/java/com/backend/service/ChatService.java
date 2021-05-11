package com.backend.service;

import com.backend.entity.Chat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    List<Chat> getChats(String from, String to);
    Chat addChat(Chat chat);
}
