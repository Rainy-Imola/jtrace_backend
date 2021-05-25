package com.backend.repository.mongo;

import com.backend.entity.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReplyRepository extends MongoRepository<Reply, Integer> {
    List<Reply> findByComment(Integer comment);
}
