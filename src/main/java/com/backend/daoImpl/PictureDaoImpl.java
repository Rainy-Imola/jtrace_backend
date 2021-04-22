package com.backend.daoImpl;

import com.backend.dao.PictureDao;
import com.backend.entity.Picture;
import com.backend.repository.mongo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDaoImpl implements PictureDao {
    @Autowired
    PictureRepository pictureRepository;

    @Override
    public Picture findById(Integer id) {
        if (pictureRepository.findById(id).isPresent()) {
            return pictureRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void addPicture(String address) {
        Picture picture = new Picture();
        picture.setAddress(address);
        picture.setId(1);
        pictureRepository.save(picture);
    }
}