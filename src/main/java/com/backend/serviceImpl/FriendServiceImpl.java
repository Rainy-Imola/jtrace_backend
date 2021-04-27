package com.backend.serviceImpl;

import com.backend.dao.FriendDao;
import com.backend.entity.Friend;
import com.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendDao friendDao;

    @Override
    public List<Friend> findByUsername(String username) {
        return friendDao.findByUsername1(username);
    }

    @Override
    public Friend findByUsername1AndUsername2(String username1, String username2) {
        return friendDao.findByUsername1AndUsername2(username1, username2);
    }

    @Override
    public void deleteFriend(String username1, String username2) {
        friendDao.deleteFriend(username1, username2);
    }

    @Override
    public void addFriend(Friend friend) {
        friendDao.addFriend(friend);
    }
}
