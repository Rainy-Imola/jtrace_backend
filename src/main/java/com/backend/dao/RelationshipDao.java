package com.backend.dao;

import com.backend.entity.FriendsRelation;
import com.backend.entity.Relationship;

import java.util.List;

public interface RelationshipDao {
    // find
    Relationship findByUsername(String username);
    List<FriendsRelation> findFriends(Long id);

    // delete user
    void deleteByUsername(String username);

    // add
    List<FriendsRelation> createFriends(String username1, String username2, Integer relationId);
}
