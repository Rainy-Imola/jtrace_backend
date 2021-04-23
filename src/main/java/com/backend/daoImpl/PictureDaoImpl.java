package com.backend.daoImpl;

import com.backend.dao.PictureDao;
import com.backend.entity.Picture;
import com.backend.repository.mongo.PictureRepository;
import com.backend.utils.mongoUtils.MongoAutoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDaoImpl implements PictureDao {
    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    MongoAutoIdUtils mongoAutoIdUtils;

    @Override
    public Picture findById(Integer id) {
        if (pictureRepository.findById(id).isPresent()) {
            return pictureRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Integer addPicture(String address) {
        Picture picture = new Picture();
        picture.setAddress(address);
        picture.setId(mongoAutoIdUtils.getNextSequence("picture"));
        pictureRepository.save(picture);

        return picture.getId();
    }
}
