package com.backend.daoImpl;

import com.backend.dao.FriendDao;
import com.backend.entity.Friend;
import com.backend.repository.jpa.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
    @Autowired
    FriendRepository friendRepository;

    @Override
    public List<Friend> findByUsername1(String username) {
        return friendRepository.findByUsername1(username);
    }

    @Override
    public Friend findByUsername1AndUsername2(String username1, String username2) {
        return friendRepository.findByUsername1AndUsername2(username1, username2);
    }

    @Override
    public void deleteFriend(String username1, String username2) {
        friendRepository.deleteFriendByUsername1AndUsername2(username1, username2);
    }

    @Override
    public void addFriend(Friend friend) {
        friendRepository.saveAndFlush(friend);
    }
}
