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

    private String from;
    private String to;

    private String message;
    private String time;
    private Boolean read;

    public Chat(Integer id, String from, String to, String message, String time, Boolean read) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.time = time;
        this.read = read;
    }

}
