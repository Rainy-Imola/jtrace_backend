package com.backend.repository.mongo;

import com.backend.entity.Message;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@CacheConfig(cacheNames = "messages")
public interface MessageRepository extends MongoRepository<Message, Integer> {
      List<Message> findByAuthor(Integer author);
}
