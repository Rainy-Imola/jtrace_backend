package com.backend.repository.mongo;

import com.backend.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, Integer> {
      List<Message> findByAuthor(Integer author);
}
