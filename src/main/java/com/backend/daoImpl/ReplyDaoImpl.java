package com.backend.daoImpl;

import com.backend.dao.ReplyDao;
import com.backend.entity.Reply;
import com.backend.repository.mongo.ReplyRepository;
import com.backend.utils.mongoUtils.MongoAutoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDaoImpl implements ReplyDao {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private MongoAutoIdUtils mongoAutoIdUtils;

    @Override
    public List<Reply> getReply(Integer comment) {
        return replyRepository.findByComment(comment);
    }

    @Override
    public Reply findById(Integer id) {
        if (replyRepository.findById(id).isPresent()) {
            return replyRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Reply releaseReply(Reply reply) {
        reply.setId(mongoAutoIdUtils.getNextSequence("reply"));
        return replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Integer id) {
        replyRepository.deleteById(id);
    }
}
