package com.backend.dao;

import com.backend.entity.Friend;

import java.util.List;

public interface FriendDao {
    List<Friend> findByUsername1(String username);
    Friend findByUsername1AndUsername2(String username1, String username2);
    void deleteFriend(String username1, String username2);
    void addFriend(Friend friend);
}
