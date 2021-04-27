package com.backend.serviceImpl;

import com.backend.dao.RelationshipDao;
import com.backend.entity.FriendsRelation;
import com.backend.entity.Relationship;
import com.backend.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    RelationshipDao relationshipDao;

    @Override
    public Relationship findByUsername(String username) {
        return relationshipDao.findByUsername(username);
    }

    @Override
    public List<FriendsRelation> findFriends(Long id) {
        return relationshipDao.findFriends(id);
    }

    @Override
    public void deleteByUsername(String username) {
        relationshipDao.deleteByUsername(username);
    }

    @Override
    public List<FriendsRelation> createFriends(String username1, String username2, Integer relationId) {
        return relationshipDao.createFriends(username1, username2, relationId);
    }
}
