package com.backend.controller;

import com.backend.entity.Friend;
import com.backend.service.FriendService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    // Add friend
    @PostMapping("/add")
    public Msg addFriend(@RequestBody JSONObject jsonObject) {
        String username1 = jsonObject.getString("username1");
        String username2 = jsonObject.getString("username2");

        Friend friend1 = new Friend();
        friend1.setUsername1(username1);
        friend1.setUsername2(username2);

        Friend friend2 = new Friend();
        friend2.setUsername1(username2);
        friend2.setUsername2(username1);

        friendService.addFriend(friend1);
        friendService.addFriend(friend2);

        Logger logger = Logger.getLogger(FriendController.class);

        logger.info("Path: /friends/add, status: success, friends: " + username1 + ", " + username2);
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    // Delete friend
    @PostMapping("/delete")
    public Msg deleteFriend(@RequestBody JSONObject jsonObject) {
        String username1 = jsonObject.getString("username1");
        String username2 = jsonObject.getString("username2");

        friendService.deleteFriend(username1, username2);
        friendService.deleteFriend(username2, username1);

        Logger logger = Logger.getLogger(FriendController.class);
        logger.info("Path: /friends/del, status: success");

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    // Get friends
    @GetMapping("/{username}")
    public Msg getFriends(@PathVariable String username) {
        List<Friend> friends = friendService.findByUsername(username);
        List<String> data = new ArrayList<>();
        for (Integer i = 0; i < friends.size(); i++) {
            data.add(friends.get(i).getUsername2());
        }

        Logger logger = Logger.getLogger(FriendController.class);
        logger.info("Path: /friends/, status: success");

        JSONArray jsonArray = JSONArray.fromObject(data);
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, jsonArray);
    }
}
