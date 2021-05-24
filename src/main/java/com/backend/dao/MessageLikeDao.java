package com.backend.dao;

import com.backend.entity.MessageLike;

public interface MessageLikeDao {
    MessageLike findByUserAndMessage(Integer user, Integer message);
    void deleteByUserAndMessage(Integer user, Integer message);
    void addCommentLike(MessageLike messageLike);
}
