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
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer author;
    private String content;
    private Date date;

    @ManyToOne
    private Message message;

    public Comment(Integer id, Integer author, String content, Date date, Message message) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.message = message;
    }
}
