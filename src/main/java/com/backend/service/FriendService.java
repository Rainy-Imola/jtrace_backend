package com.backend.service;

import com.backend.entity.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> findByUsername(String username);
    Friend findByUsername1AndUsername2(String username1, String username2);
    void deleteFriend(String username1, String username2);
    void addFriend(Friend friend);
}
