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

    private Integer author;
    private String content;
    private Date date;
    private String picture;

    public Message(Integer id, Integer author, String message, Date date) {
        this.id = id;
        this.author = author;
        this.content = message;
        this.date = date;
        this.picture = null;
    }

    public Message(Integer id, Integer author, String message, Date date, String picture) {
        this.id = id;
        this.author = author;
        this.content = message;
        this.date = date;
        this.picture = picture;
    }
}
