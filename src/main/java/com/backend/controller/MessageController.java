package com.backend.controller;

import com.backend.entity.*;
import com.backend.service.*;
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
    private MessageLikeService messageLikeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

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
    @GetMapping("/{user}")
    public Msg getMessages(@PathVariable Integer user) {
        List<Message> messages = messageService.getMessages();

        JSONArray data = new JSONArray();
        List<Integer> randomList = RandomList.randomList(0, messages.size(), 20);
        for (Integer i: randomList) {
            Message message = messages.get(i);
            Integer authorId = message.getAuthor();
            String authorName = userService.findById(authorId).getUsername();
            String date = message.getDate();

            JSONObject object = new JSONObject();
            object.put("id", message.getId());
            object.put("author", authorName);
            object.put("content", message.getContent());
            object.put("date", date);
            object.put("picture", message.getPicture());
            object.put("like", message.getLike());

            Integer accept = 0;
            if (messageLikeService.findByUserAndMessage(user, message.getId()) != null) {
                accept = 1;
            }
            object.put("accept", accept);

            data.add(object);
        }

        Logger logger = Logger.getLogger(MessageController.class);
        logger.info("Path: /msgboard/, status: success");
        
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }

    // get message
    @GetMapping("/{id}/{user}")
    public Msg getMessage(@PathVariable Integer id, @PathVariable Integer user) {
        Message message = messageService.findById(id);
        List<Comment> comments = commentService.getComments(id);
        List<JSONObject> jsonList = new ArrayList<>();
        for (Comment comment: comments) {
            JSONObject object = new JSONObject();
            Integer author_id = comment.getAuthor();
            String authorName = userService.findById(author_id).getUsername();
            List<Reply> replies = replyService.getReply(comment.getId());
            object.put("id", comment.getId());
            object.put("author", authorName);
            object.put("content", comment.getContent());
            object.put("date", comment.getDate());
            object.put("reply", replies);

            jsonList.add(object);
        }

        Integer accept = 0;
        if (messageLikeService.findByUserAndMessage(user, id) != null) {
            accept = 1;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", (Message) message);
        jsonObject.put("accept", (Integer) accept);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        Integer like = 0;

        Message message = new Message();
        message.setAuthor(author);
        message.setContent(content);
        message.setDate(time);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);

        message.setContent(content);
        message.setDate(time);
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
        Integer user = jsonObject.getInt("user");
        if (like == 1) {
            messageService.likeMessage(id);

            MessageLike messageLike = new MessageLike();
            messageLike.setMessage(id);
            messageLike.setUser(user);
            messageLikeService.addCommentLike(messageLike);
        } else if (like == 0) {
            messageService.unlikeMessage(id);
            messageLikeService.deleteByUserAndMessage(user, id);
        } else {
            logger.error("Path: /like/" + id + ", status: fail");
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }

        logger.info("Path: /like/" + id + ", status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
