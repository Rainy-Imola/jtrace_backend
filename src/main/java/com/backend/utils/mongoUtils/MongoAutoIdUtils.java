package com.backend.utils.mongoUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Component
public class MongoAutoIdUtils {
    @Autowired
    MongoTemplate mongo;

    public Integer getNextSequence(String collectionName) {
        MongoSequence seq = mongo.findAndModify(
                query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().upsert(true).returnNew(true),
                MongoSequence.class);
        return seq.getSeq();
    }
}
