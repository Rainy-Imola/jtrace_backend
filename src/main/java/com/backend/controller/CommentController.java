package com.backend.controller;

import com.backend.entity.Comment;
import com.backend.entity.Message;
import com.backend.service.CommentService;
import com.backend.service.MessageService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/msgboard")
public class CommentController {
    @Autowired
    MessageService messageService;

    @Autowired
    CommentService commentService;

    @PostMapping("/{message_id}/comments/release")
    public Msg releaseComemnt(@PathVariable Integer message_id, @RequestBody JSONObject jsonObject) {
        Integer author = jsonObject.getInt("author");
        String content = jsonObject.getString("content");
        Date date = new Date();
        Message message = messageService.findById(message_id);

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setDate(date);
        comment.setMessage(message);

        commentService.releaseComment(comment);

        Logger logger = Logger.getLogger(CommentController.class);
        logger.info("Path: /msgboard/" + message_id + "comments/release" + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @PostMapping("/{message_id}/comments/{id}")
    public Msg updateComment(@PathVariable Integer message_id, @PathVariable Integer id, @RequestBody JSONObject jsonObject) {
        Comment comment = commentService.findById(id);

        Date date = new Date();
        String content = jsonObject.getString("content");

        comment.setContent(content);
        comment.setDate(date);

        commentService.updateComment(comment);

        Logger logger = Logger.getLogger(CommentController.class);
        logger.info("Path: /msgboard/" + message_id + "comments/" + id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @DeleteMapping("/{message_id}/comments/{id}")
    public Msg deleteComment(@PathVariable Integer message_id, @PathVariable Integer id) {
        commentService.deleteComment(id);

        Logger logger = Logger.getLogger(CommentController.class);
        logger.info("Path: /msgboard/" + message_id + "comments/" + id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
