package com.backend.daoImpl;

import com.backend.dao.MessageLikeDao;
import com.backend.entity.MessageLike;
import com.backend.repository.jpa.MessageLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MessageLikeDaoImpl implements MessageLikeDao {
    @Autowired
    private MessageLikeRepository messageLikeRepository;

    @Override
    public MessageLike findByUserAndMessage(Integer user, Integer message) {
        return messageLikeRepository.findCommentLikeByUserAndMessage(user, message);
    }

    @Override
    public void deleteByUserAndMessage(Integer user, Integer message) {
        messageLikeRepository.deleteMessageLikeByUserAndMessage(user, message);
    }

    @Override
    public void addCommentLike(MessageLike messageLike) {
        messageLikeRepository.save(messageLike);
    }
}
