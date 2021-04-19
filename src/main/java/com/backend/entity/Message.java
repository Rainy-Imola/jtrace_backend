package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;
    private String message;
    private Date date;

    public Message(Integer id, User user, String message, Date date) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.date = date;
    }
}
