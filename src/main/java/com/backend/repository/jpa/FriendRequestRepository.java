package com.backend.repository.jpa;

import com.backend.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {
    List<FriendRequest> findFriendRequestsByUsername2(String username2);
    List<FriendRequest> findFriendRequestsByUsername1(String username1);
    FriendRequest findFriendRequestByUsername1AndUsername2(String username1, String username2);
}
