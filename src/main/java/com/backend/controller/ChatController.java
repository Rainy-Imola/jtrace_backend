package com.backend.controller;

import com.backend.entity.Chat;
import com.backend.service.ChatService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/getchat")
    public Msg getChat(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to) {
        List<Chat> chats = chatService.getChats(from, to);
        JSONArray data = JSONArray.fromObject(chats);

        Logger logger = Logger.getLogger(ChatController.class);
        logger.info("Path: /chat/getchat, status = success");

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }

    @PostMapping("/sendchat")
    public Msg sendChat(@RequestBody JSONObject jsonObject) {
        String message = jsonObject.getString("message");
        String from = jsonObject.getString("from");
        String to = jsonObject.getString("to");
        Date date = new Date();
        Boolean read = false;

        Chat chat = new Chat();
        chat.setFrom(from);
        chat.setTo(to);
        chat.setMessage(message);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        chat.setTime(time);
        chat.setRead(read);

        chatService.addChat(chat);

        Logger logger = Logger.getLogger(ChatController.class);
        logger.info("Path: /chat/sendchat, status: success");

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
