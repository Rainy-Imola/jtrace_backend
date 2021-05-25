package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer comment;
    private String author;
    private String content;
    private String date;

    private Integer level;

    public Reply(Integer id, Integer comment, String author, String content, String date, Integer level) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.content = content;
        this.date = date;
        this.level = level;
    }
}
