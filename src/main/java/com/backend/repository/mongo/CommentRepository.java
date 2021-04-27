package com.backend.repository.mongo;

import com.backend.entity.Comment;
import com.backend.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Integer> {
    List<Comment> findCommentsByMessage(Message message);
}
