package com.backend.service;

import com.backend.entity.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReply(Integer comment);
    Reply findById(Integer id);
    Reply releaseReply(Reply reply);
    void deleteReply(Integer id);
}
