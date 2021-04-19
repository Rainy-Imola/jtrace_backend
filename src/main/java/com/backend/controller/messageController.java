package com.backend.controller;

import com.backend.dao.MessageDao;
import com.backend.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/msgboard")
public class messageController {

    @Autowired
    private MessageDao messageDao;

    // create message
    @PostMapping("/")
    public String postMessage(@RequestBody Message message) {
        message.setDate(new Date());
        messageDao.save(message);
        return "success";
    }

    // get all messages
    @GetMapping("/")
    public List<Message> getAllMessage() {
        List<Message> messages = messageDao.findAll();
        return messages;
    }

    // get message
    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Integer id) {
        Message message = messageDao.findById(id).get();
        return message;
    }

    // delete message
    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Integer id) {
        messageDao.deleteById(id);
        return "success";
    }

    // update message
    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Integer id, @RequestBody Message msg) {
        Message message = messageDao.findById(id).get();
        message.setMessage(msg.getMessage());
        message.setDate(new Date());
        messageDao.save(message);
        return message;
    }
}
