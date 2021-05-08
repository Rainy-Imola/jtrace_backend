package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class FriendRequest {
    @Id
    @GeneratedValue
    private Integer id;

    private String username1;   // request user
    private String username2;   // response user
    private Integer status;     // unhandled, reject or accept

    public FriendRequest(Integer id, String username1, String username2, Integer status) {
        this.id = id;
        this.username1 = username1;
        this.username2 = username2;
        this.status = status;
    }
}
