package com.backend.utils.mongoUtils;

import javax.persistence.Id;

public class MongoSequence {
    @Id
    private String id;

    private Integer seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
