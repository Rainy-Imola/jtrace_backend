package com.backend.service;

import com.backend.entity.MessageLike;

public interface MessageLikeService {
    MessageLike findByUserAndMessage(Integer user, Integer message);
    void deleteByUserAndMessage(Integer user, Integer message);
    void addCommentLike(MessageLike messageLike);
}
