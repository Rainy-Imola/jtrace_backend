package com.backend.service;

import com.backend.entity.Friend;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "friends")
public interface FriendService {
    List<Friend> findByUsername(String username);
    @Cacheable(key = "#p0")
    Friend findByUsername1AndUsername2(String username1, String username2);
    @CacheEvict(key = "#p0", allEntries = true)
    void deleteFriend(String username1, String username2);
    @CachePut(value = "friends")
    void addFriend(Friend friend);
}
