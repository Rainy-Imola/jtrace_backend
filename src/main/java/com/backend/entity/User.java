package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    private String password;
    private String email;
    private String hobby;
    private String constellation;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = null;
        this.hobby = null;
        this.constellation = null;
    }
}
