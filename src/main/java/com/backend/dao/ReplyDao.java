package com.backend.dao;

import com.backend.entity.Reply;

import java.util.List;

public interface ReplyDao {
    List<Reply> getReply(Integer comment);
    Reply findById(Integer id);
    Reply releaseReply(Reply reply);
    void deleteReply(Integer id);
}