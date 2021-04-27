package com.backend.repository.neo4j;

import com.backend.entity.FriendsRelation;
import com.backend.entity.Relationship;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface FriendsRelationRepository extends Neo4jRepository<FriendsRelation, Long> {
    @Query("MATCH p = (n)-[r:Friend]->(b) return p")
    List<FriendsRelation> getFriends();

    @Query("MATCH (a), (b) WHERE a.id={0} and b.id={1}"
            + " CREATE p = (a)-[r:Friend{relationID:{2}}]->(b) return p")
    List<FriendsRelation> createFriends(Long startNodeId, Long endNodeId, Integer relationId);
}
