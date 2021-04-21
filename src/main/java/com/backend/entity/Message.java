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
    private User author;
    private String content;
    private Date date;

    public Message(Integer id, User author, String message, Date date) {
        this.id = id;
        this.author = author;
        this.content = message;
        this.date = date;
    }
}
