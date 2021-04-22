package com.backend.repository.mongo;

import com.backend.entity.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PictureRepository extends MongoRepository<Picture, Integer> {
}
