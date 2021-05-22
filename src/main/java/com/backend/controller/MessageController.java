package com.backend.controller;

import com.backend.entity.Comment;
import com.backend.entity.Message;
import com.backend.entity.User;
import com.backend.service.CommentService;
import com.backend.service.MessageService;
import com.backend.service.UserService;
import com.backend.utils.RandomUtils.RandomList;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/msgboard")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // get messages by author
    @GetMapping()
    public Msg findByAuthor(@RequestParam String author) {
        Logger logger = Logger.getLogger(MessageController.class);

        User user = userService.findByName(author);

        if (user != null) {
            List<Message> data = messageService.findByAuthor(user.getId());
            System.out.println(data);
            JSONArray jsonArray = JSONArray.fromObject(data);

            logger.info("Path: /msgboard/" + author + ", status: success");
            return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, jsonArray);
        } else {
            logger.error("Path: /msgboard/" + author + ", status: fail!");
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }
    }

    // get messages
    @GetMapping("/")
    public Msg getMessages() {
        List<Message> messages = messageService.getMessages();

        JSONArray data = new JSONArray();
        List<Integer> randomList = RandomList.randomList(0, messages.size(), 20);
        for (Integer i: randomList) {
            Message message = messages.get(i);
            Integer authorId = message.getAuthor();
            String authorName = userService.findById(authorId).getUsername();

            Date date = message.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss.SSSXXX");

            String str = sdf.format(date);

            JSONObject object = new JSONObject();
            object.put("id", message.getId());
            object.put("author", authorName);
            object.put("content", message.getContent());
            object.put("date", str);
            object.put("picture", message.getPicture());
            object.put("like", message.getLike());

            data.add(object);
        }

        Logger logger = Logger.getLogger(MessageController.class);
        logger.info("Path: /msgboard/, status: success");
        
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }

    // get message
    @GetMapping("/{id}")
    public Msg getMessage(@PathVariable Integer id) {
        Message message = messageService.findById(id);
        List<Comment> comments = commentService.getComments(id);
        List<JSONObject> jsonList = new ArrayList<>();
        for (Comment comment: comments) {
            JSONObject object = new JSONObject();
            Integer author_id = comment.getAuthor();
            String authorName = userService.findById(author_id).getUsername();
            object.put("author", authorName);
            object.put("content", comment.getContent());
            object.put("date", comment.getDate());

            jsonList.add(object);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", (Message) message);
        jsonObject.put("comment", (List<JSONObject>) jsonList);
        JSONArray data = JSONArray.fromObject(jsonObject);
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }

    // release message
    @PostMapping("/release")
    public Msg releaseMessage(@RequestBody JSONObject jsonObject) {
        Integer author = jsonObject.getInt("author");
        String content = jsonObject.getString("content");
        String picture = (String) jsonObject.get("picture");
        Date date = new Date();
        Integer like = 0;

        Message message = new Message();
        message.setAuthor(author);
        message.setContent(content);
        message.setDate(date);
        message.setLike(like);
        if (picture != null) {
            message.setPicture(picture);
        }

        messageService.releaseMessage(message);

        Logger logger = Logger.getLogger(MessageController.class);
        logger.info("Path: /release, status: success, author: " + author);
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    @PutMapping("/{id}")
    public Msg updateMessage(@PathVariable Integer id, @RequestBody JSONObject jsonObject) {
        Message message = messageService.findById(id);
        String content = jsonObject.getString("content");
        String picture = (String) jsonObject.get("picture");
        Date date = new Date();

        message.setContent(content);
        message.setDate(date);
        if (picture != null) {
            message.setPicture(picture);
        }

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

    @PostMapping("/like/{id}")
    public Msg likeMessage(@PathVariable Integer id, @RequestBody JSONObject jsonObject) {
        Logger logger = Logger.getLogger(MessageController.class);

        Integer like = jsonObject.getInt("like");
        if (like == 1) {
            messageService.likeMessage(id);
        } else if (like == 0) {
            messageService.unlikeMessage(id);
        } else {
            logger.error("Path: /like/" + id + ", status: fail");
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }

        logger.info("Path: /like/" + id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
