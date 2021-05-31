package com.backend.controller;

import com.backend.entity.Friend;
import com.backend.entity.FriendRequest;
import com.backend.service.FriendRequestService;
import com.backend.service.FriendService;
import com.backend.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private FriendRequestService friendRequestService;

    // get friend requests
    @GetMapping("/requests/{username}")
    public Msg getRequests(@PathVariable String username) {
        List<FriendRequest> requests = friendRequestService.findByUsername2(username);

        JSONArray data = JSONArray.fromObject(requests);

        Logger logger = Logger.getLogger(FriendController.class);
        logger.info("Path: /requests/" + username + " status: success");

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }

    // get friend response
    @GetMapping("/responses/{username}")
    public Msg getResponse(@PathVariable String username) {
        List<FriendRequest> responses = friendRequestService.findByUsername1(username);

        JSONArray data = JSONArray.fromObject(responses);

        Logger logger = Logger.getLogger(FriendController.class);
        logger.info("Path: /responses/" + username + " status: success");

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }


    // request for a friend
    @PostMapping("/request")
    public Msg requestFriend(@RequestBody JSONObject jsonObject) {
        String username1 = jsonObject.getString("username1");
        String username2 = jsonObject.getString("username2");
        String reqMsg = jsonObject.getString("reqMsg");

        FriendRequest request = friendRequestService.findByUsernames(username1, username2);
        if (request != null) {
            request.setStatus(0);
            request.setReqMsg(reqMsg);

            friendRequestService.addRequest(request);
        } else {
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setUsername1(username1);
            friendRequest.setUsername2(username2);
            friendRequest.setReqMsg(reqMsg);
            friendRequest.setStatus(0);

            friendRequestService.addRequest(friendRequest);
        }

        Logger logger = Logger.getLogger(FriendController.class);
        logger.info("Path: /request, status: success");

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    // response to a friend request
    @PostMapping("/response")
    public Msg responseFriend(@RequestBody JSONObject jsonObject) {
        String username1 = jsonObject.getString("username1");
        String username2 = jsonObject.getString("username2");
        Integer status = jsonObject.getInt("status");

        FriendRequest friendRequest = friendRequestService.findByUsernames(username1, username2);
        friendRequest.setStatus(status);

        if (status == 1) {  // if accept
            Friend friend1 = new Friend();
            friend1.setUsername1(username1);
            friend1.setUsername2(username2);

            Friend friend2 = new Friend();
            friend2.setUsername1(username2);
            friend2.setUsername2(username1);

            friendService.addFriend(friend1);
            friendService.addFriend(friend2);
        }

        friendRequestService.setStatus(friendRequest);

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

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
        List<JSONObject> data = new ArrayList<>();
        for (Integer i = 0; i < friends.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", friends.get(i).getUsername2());
            jsonObject.put("status", userService.getUserStatus(friends.get(i).getUsername2()));
            data.add(jsonObject);
        }

        Logger logger = Logger.getLogger(FriendController.class);
        logger.info("Path: /friends/, status: success");

        JSONArray jsonArray = JSONArray.fromObject(data);
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, jsonArray);
    }

    @PostMapping("/check")
    public Integer checkFriends(@RequestBody JSONObject jsonObject) {
        String username1 = jsonObject.getString("username1");
        String username2 = jsonObject.getString("username2");

        Friend friend = friendService.findByUsername1AndUsername2(username1, username2);
        if (friend == null) {
            return 0;
        } else {
            System.out.println(friend.getUsername1());
            return 1;
        }
    }
}
