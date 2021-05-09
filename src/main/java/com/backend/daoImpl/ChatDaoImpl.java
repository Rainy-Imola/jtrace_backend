package com.backend.daoImpl;

import com.backend.dao.ChatDao;
import com.backend.entity.Chat;
import com.backend.repository.mongo.ChatRepository;
import com.backend.utils.mongoUtils.MongoAutoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDaoImpl implements ChatDao {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MongoAutoIdUtils mongoAutoIdUtils;

    @Override
    public List<Chat> getChats(String from, String to) {
        return chatRepository.getChatsByFromAndTo(from, to);
    }

    @Override
    public Chat addChat(Chat chat) {
        chat.setId(mongoAutoIdUtils.getNextSequence("chat"));
        return chatRepository.save(chat);
    }
}
