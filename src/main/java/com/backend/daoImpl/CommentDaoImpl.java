package com.backend.daoImpl;

import com.backend.dao.CommentDao;
import com.backend.entity.Comment;
import com.backend.entity.Message;
import com.backend.repository.mongo.CommentRepository;
import com.backend.repository.mongo.MessageRepository;
import com.backend.utils.mongoUtils.MongoAutoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MongoAutoIdUtils mongoAutoIdUtils;

    @Override
    public List<Comment> getComments(Integer message) {
        List<Comment> comments = commentRepository.findCommentsByMessage(message);
        return comments;
    }

    @Override
    public Comment findById(Integer id) {
        if (commentRepository.findById(id).isPresent()) {
            return commentRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Comment releaseComment(Comment comment) {
        comment.setId(mongoAutoIdUtils.getNextSequence("comment"));
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
