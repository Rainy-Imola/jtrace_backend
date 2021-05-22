package com.backend.controller;

import com.backend.entity.Friend;
import com.backend.entity.User;
import com.backend.service.FriendService;
import com.backend.service.UserService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.Map.Entry;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @GetMapping("/friends/{username}")
    public Msg mostFriendInCommon(@PathVariable String username) {
        // 1. Get all friends of user
        User user = userService.findByName(username);
        List<Friend> friends = friendService.findByUsername(user.getUsername());
        List<String> friendNames = new ArrayList<>();
        for (Friend friend: friends) {
            friendNames.add(friend.getUsername2());
        }

        // 2. Get friends of users, who is not the friend of user
        List<User> users = userService.getUsers();
        List<User> unknown = new ArrayList<>();
        for (User u: users) {
            if (!friendNames.contains(u.getUsername()) && !u.getUsername().equals(username)) {
                unknown.add(u);
            }
        }

        for (User u: unknown) {
            System.out.println(u.getUsername());
        }

        // 3. calculate top 5 users who has most friends in common with user
        HashMap<User, Integer> friendsInCommon = new HashMap<>();
        for (User un: unknown) {
            List<Friend> unf = friendService.findByUsername(un.getUsername());
            List<String> unfNames = new ArrayList<>();
            for (Friend friend: unf) {
                unfNames.add(friend.getUsername2());
            }

            unfNames.retainAll(friendNames);
            friendsInCommon.put(un, unfNames.size());
        }

        List<Map.Entry<User, Integer>> list = new ArrayList<Map.Entry<User, Integer>>(friendsInCommon.entrySet());
        list.sort(new Comparator<Entry<User, Integer>>() {
            @Override
            public int compare(Entry<User, Integer> o1, Entry<User, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        List<User> recomList = new ArrayList<>();
        for (Map.Entry<User, Integer> mapping: list) {
            recomList.add(mapping.getKey());
        }

        JSONArray data = JSONArray.fromObject(recomList.subList(0, 5));

        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }
}
