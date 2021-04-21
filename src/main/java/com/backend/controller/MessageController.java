package com.backend.controller;

import com.backend.entity.Message;
import com.backend.entity.User;
import com.backend.service.MessageService;
import com.backend.service.UserService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/msgboard")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    // get messages by author
    @GetMapping("/{author}")
    public Msg findByAuthor(@PathVariable String author) {
        User user = userService.findByName(author);

        Logger logger = Logger.getLogger(MessageController.class);

        if (user != null) {
            List<Message> data = messageService.findByAuthor(userService.findByName(author));
            JSONObject jsonObject = JSONObject.fromObject(data);

            logger.info("Path: /msgboard/" + author + ", status: success");
            return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, jsonObject);
        } else {
            logger.error("Path: /msgboard/" + author + ", status: fail!");
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }
    }

    // get message
    @GetMapping("/")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    // release message
    @PostMapping("/release")
    public Msg releaseMessage(@RequestBody JSONObject jsonObject) {
        User author = userService.findByName(jsonObject.getString("author"));
        String content = jsonObject.getString("content");
        Date date = new Date();

        Message message = new Message();
        message.setAuthor(author);
        message.setContent(content);
        message.setDate(date);

        messageService.releaseMessage(message);

        Logger logger = Logger.getLogger(MessageController.class);
        logger.info("Path: /release, status: success, author: " + author);
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @PutMapping("/{id}")
    public Msg updateMessage(@PathVariable Integer id, @RequestBody JSONObject jsonObject) {
        Message message = messageService.findById(id);
        String content = jsonObject.getString("content");
        Date date = new Date();

        message.setContent(content);
        message.setDate(date);

        messageService.updateMessage(message);

        Logger logger = Logger.getLogger(MessageController.class);
        logger.info("Path: /msgboard/" + id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @DeleteMapping("/{id}")
    public Msg deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);

        Logger logger = Logger.getLogger(MessageController.class);
        logger.info("Path: /msgboard/" + id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
