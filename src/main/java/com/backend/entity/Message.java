package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Integer picture_id;

    public Message(Integer id, User author, String message, Date date) {
        this.id = id;
        this.author = author;
        this.content = message;
        this.date = date;
        this.picture_id = null;
    }

    public Message(Integer id, User author, String message, Date date, Integer picture_id) {
        this.id = id;
        this.author = author;
        this.content = message;
        this.date = date;
        this.picture_id = picture_id;
    }
}
