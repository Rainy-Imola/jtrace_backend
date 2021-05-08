package com.backend.dao;

import com.backend.entity.FriendRequest;

import java.util.List;

public interface FriendRequestDao {
    List<FriendRequest> findByUsername2(String username);
    List<FriendRequest> findByUsername1(String username);
    FriendRequest findByUsernames(String username1, String username2);
    FriendRequest setStatus(FriendRequest friendRequest);
    FriendRequest addRequest(String username1, String username2);

}
