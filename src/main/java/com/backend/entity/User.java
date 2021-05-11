package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

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
    @ElementCollection
    private List<String> hobby;
    private String constellation;

    private Boolean status; // online or offline

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = null;
        this.hobby = null;
        this.constellation = null;
    }
}
