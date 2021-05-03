package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Captcha {
    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String captcha;

    public Captcha(Integer id, String email, String captcha) {
        this.id = id;
        this.email = email;
        this.captcha = captcha;
    }
}
