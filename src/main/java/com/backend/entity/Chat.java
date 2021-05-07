package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer from;
    private Integer to;

    private String message;
    private Date time;

    public Chat(Integer id, Integer from, Integer to, String message, Date time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.time = time;
    }

}
