package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue
    private Integer id;

    private String username1;
    private String username2;

    public Friend(Integer id, String username1, String username2) {
        this.id = id;
        this.username1 = username1;
        this.username2 = username2;
    }
}
