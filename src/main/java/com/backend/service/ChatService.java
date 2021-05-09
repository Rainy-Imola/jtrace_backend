package com.backend.service;

import com.backend.entity.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> getChats(String from, String to);
    Chat addChat(Chat chat);
}
