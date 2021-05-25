package com.backend.controller;

import com.backend.entity.Reply;
import com.backend.service.ReplyService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/release")
    public Msg releaseReply(@RequestBody JSONObject jsonObject) {
        Integer comment = jsonObject.getInt("comment");
        String author = jsonObject.getString("author");
        String content = jsonObject.getString("content");
        Integer level = jsonObject.getInt("level");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);

        Reply reply = new Reply();
        reply.setComment(comment);
        reply.setAuthor(author);
        reply.setContent(content);
        reply.setDate(time);
        reply.setLevel(level);

        replyService.releaseReply(reply);

        Logger logger = Logger.getLogger(ReplyController.class);
        logger.info("Path: /reply/release" + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @DeleteMapping("/{reply_id}")
    public Msg deleteReply(@PathVariable Integer reply_id) {
        replyService.deleteReply(reply_id);

        Logger logger = Logger.getLogger(ReplyController.class);
        logger.info("Path: /reply/" + reply_id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
