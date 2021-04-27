package com.backend.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "Friend")
public class FriendsRelation {

    @Id
    private Long id;

    @StartNode
    private Relationship startNode;

    @EndNode
    private Relationship endNode;

    @Property(name = "relationID")
    private Integer relationID;

    public FriendsRelation() {
    }

    public FriendsRelation(Relationship startNode, Relationship endNode, Integer relationID) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.relationID = relationID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Relationship getStartNode() {
        return startNode;
    }

    public void setStartNode(Relationship startNode) {
        this.startNode = startNode;
    }

    public Relationship getEndNode() {
        return endNode;
    }

    public void setEndNode(Relationship endNode) {
        this.endNode = endNode;
    }

    public Integer getRelationID() {
        return relationID;
    }

    public void setRelationID(Integer relationID) {
        this.relationID = relationID;
    }
}
