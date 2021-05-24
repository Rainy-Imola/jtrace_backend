package com.backend.serviceImpl;

import com.backend.dao.MessageLikeDao;
import com.backend.entity.MessageLike;
import com.backend.service.MessageLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageLikeServiceImpl implements MessageLikeService {
    @Autowired
    private MessageLikeDao messageLikeDao;

    @Override
    public MessageLike findByUserAndMessage(Integer user, Integer message) {
        return messageLikeDao.findByUserAndMessage(user, message);
    }

    @Override
    public void deleteByUserAndMessage(Integer user, Integer message) {
        messageLikeDao.deleteByUserAndMessage(user, message);
    }

    @Override
    public void addCommentLike(MessageLike messageLike) {
        messageLikeDao.addCommentLike(messageLike);
    }
}
