package com.backend.serviceImpl;

import com.backend.dao.ReplyDao;
import com.backend.entity.Reply;
import com.backend.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Override
    public List<Reply> getReply(Integer comment) {
        return replyDao.getReply(comment);
    }

    @Override
    public Reply findById(Integer id) {
        return replyDao.findById(id);
    }

    @Override
    public Reply releaseReply(Reply reply) {
        return replyDao.releaseReply(reply);
    }

    @Override
    public void deleteReply(Integer id) {
        replyDao.deleteReply(id);
    }
}
