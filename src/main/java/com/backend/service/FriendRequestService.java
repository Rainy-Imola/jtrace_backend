package com.backend.service;

import com.backend.entity.FriendRequest;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> findByUsername2(String username);
    List<FriendRequest> findByUsername1(String username);
    FriendRequest findByUsernames(String username1, String username2);
    FriendRequest setStatus(FriendRequest friendRequest);
    FriendRequest addRequest(FriendRequest friendRequest);
}
