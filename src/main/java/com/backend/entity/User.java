package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    private String password;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> hobby = new ArrayList<>();
    private String constellation;
    private String avatar;

    private Boolean status; // online or offline

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = null;
        this.hobby = null;
        this.constellation = null;
        this.avatar = null;
    }
}
