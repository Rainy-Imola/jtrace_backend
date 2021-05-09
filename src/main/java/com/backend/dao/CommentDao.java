package com.backend.dao;

import com.backend.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getComments(Integer message);
    Comment findById(Integer id);
    Comment releaseComment(Comment comment);
    void deleteComment(Integer id);
    Comment updateComment(Comment comment);
}
