package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class MessageLike {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer user;
    private Integer message;

    public MessageLike(Integer id, Integer user, Integer message) {
        this.id = id;
        this.user = user;
        this.message = message;
    }
}
