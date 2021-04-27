package com.backend.entity;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Relationship {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    public Relationship() {
    }

    @org.neo4j.ogm.annotation.Relationship(type = "Friend", direction = org.neo4j.ogm.annotation.Relationship.OUTGOING)
    private List<FriendsRelation> friends;

    public void addFriends(Relationship relationship, Integer relationID) {
        FriendsRelation friend = new FriendsRelation(this, relationship, relationID);
        this.friends.add(friend);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<FriendsRelation> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendsRelation> friends) {
        this.friends = friends;
    }
}
