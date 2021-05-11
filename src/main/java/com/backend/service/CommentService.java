package com.backend.service;

import com.backend.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(Integer message);
    Comment findById(Integer id);
    Comment releaseComment(Comment comment);
    void deleteComment(Integer id);
    Comment updateComment(Comment comment);
}
