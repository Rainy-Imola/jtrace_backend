package com.backend.serviceImpl;

import com.backend.dao.FriendRequestDao;
import com.backend.entity.FriendRequest;
import com.backend.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    private FriendRequestDao friendRequestDao;

    @Override
    public List<FriendRequest> findByUsername2(String username) {
        return friendRequestDao.findByUsername2(username);
    }

    @Override
    public List<FriendRequest> findByUsername1(String username) {
        return friendRequestDao.findByUsername1(username);
    }

    @Override
    public FriendRequest findByUsernames(String username1, String username2) {
        return friendRequestDao.findByUsernames(username1, username2);
    }

    @Override
    public FriendRequest setStatus(FriendRequest friendRequest) {
        return friendRequestDao.setStatus(friendRequest);
    }

    @Override
    public FriendRequest addRequest(FriendRequest friendRequest) {
        return friendRequestDao.addRequest(friendRequest);
    }
}
