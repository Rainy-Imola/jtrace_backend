package com.backend.repository.mongo;

import com.backend.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, Integer> {
    List<Chat> getChatsByFromAndTo(String from, String to);
}
