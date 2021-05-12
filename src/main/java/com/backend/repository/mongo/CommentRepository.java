package com.backend.repository.mongo;

import com.backend.entity.Comment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@CacheConfig(cacheNames = "comments")
public interface CommentRepository extends MongoRepository<Comment, Integer> {
    @Cacheable
    List<Comment> findCommentsByMessage(Integer message);
}
