package com.backend.daoImpl;

import com.backend.dao.RelationshipDao;
import com.backend.entity.FriendsRelation;
import com.backend.entity.Relationship;
import com.backend.repository.neo4j.FriendsRelationRepository;
import com.backend.repository.neo4j.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelationshipDaoImpl implements RelationshipDao {
    @Autowired
    RelationshipRepository relationshipRepository;

    @Autowired
    FriendsRelationRepository friendsRelationRepository;

    @Override
    public Relationship findByUsername(String username) {
        return relationshipRepository.findByUsername(username);
    }

    @Override
    public List<FriendsRelation> findFriends(Long id) {
        if (relationshipRepository.findById(id).isPresent()) {
            Relationship relationship = relationshipRepository.findById(id).get();
            return relationship.getFriends();
        } else {
            return null;
        }
    }

    @Override
    public void deleteByUsername(String username) {
        relationshipRepository.deleteByUsername(username);
    }

    @Override
    public List<FriendsRelation> createFriends(String username1, String username2, Integer relationId) {
        Relationship startNode = relationshipRepository.findByUsername(username1);
        Relationship endNode = relationshipRepository.findByUsername(username2);

        return friendsRelationRepository.createFriends(startNode.getId(), endNode.getId(), relationId);
    }
}
