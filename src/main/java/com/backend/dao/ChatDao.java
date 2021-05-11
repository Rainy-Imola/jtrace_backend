package com.backend.dao;

import com.backend.entity.Chat;

import java.util.List;

public interface ChatDao {
    List<Chat> getChats(String from, String to);
    Chat addChat(Chat chat);
}
