package com.backend.repository.neo4j;

import com.backend.entity.FriendsRelation;
import com.backend.entity.Relationship;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface RelationshipRepository extends Neo4jRepository<Relationship, Long> {
    Relationship findByUsername(String username);
    Long deleteByUsername(String username);
}
