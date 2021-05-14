package com.backend.daoImpl;

import com.backend.dao.FriendRequestDao;
import com.backend.entity.FriendRequest;
import com.backend.repository.jpa.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRequestDaoImpl implements FriendRequestDao {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Override
    public List<FriendRequest> findByUsername2(String username) {
        return friendRequestRepository.findFriendRequestsByUsername2(username);
    }

    @Override
    public List<FriendRequest> findByUsername1(String username) {
        return friendRequestRepository.findFriendRequestsByUsername1(username);
    }

    @Override
    public FriendRequest findByUsernames(String username1, String username2) {
        return friendRequestRepository.findFriendRequestByUsername1AndUsername2(username1, username2);
    }

    @Override
    public FriendRequest setStatus(FriendRequest friendRequest) {
        return friendRequestRepository.saveAndFlush(friendRequest);
    }

    @Override
    public FriendRequest addRequest(FriendRequest friendRequest) {
        return friendRequestRepository.saveAndFlush(friendRequest);
    }
}
