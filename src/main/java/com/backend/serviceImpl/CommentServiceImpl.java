package com.backend.serviceImpl;

import com.backend.dao.CommentDao;
import com.backend.entity.Comment;
import com.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> getComments(Integer message_id) {
        return commentDao.getComments(message_id);
    }

    @Override
    public Comment findById(Integer id) {
        return commentDao.findById(id);
    }

    @Override
    public Comment releaseComment(Comment comment) {
        return commentDao.releaseComment(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentDao.deleteComment(id);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }
}
