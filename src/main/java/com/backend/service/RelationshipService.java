package com.backend.service;

import com.backend.entity.FriendsRelation;
import com.backend.entity.Relationship;

import java.util.List;

public interface RelationshipService {
    Relationship findByUsername(String username);
    List<FriendsRelation> findFriends(Long id);

    void deleteByUsername(String username);

    List<FriendsRelation> createFriends(String username1, String username2, Integer relationId);
}
