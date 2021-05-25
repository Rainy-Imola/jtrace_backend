package com.backend.repository.jpa;

import com.backend.entity.MessageLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageLikeRepository extends JpaRepository<MessageLike, Integer> {
    MessageLike findCommentLikeByUserAndMessage(Integer user, Integer message);
    void deleteMessageLikeByUserAndMessage(Integer user, Integer message);
}
