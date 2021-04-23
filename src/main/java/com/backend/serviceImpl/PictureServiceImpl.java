package com.backend.serviceImpl;

import com.backend.dao.PictureDao;
import com.backend.entity.Picture;
import com.backend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureDao pictureDao;

    @Override
    public Picture findById(Integer id) {
        return pictureDao.findById(id);
    }

    @Override
    public void addPicture(String address) {
        pictureDao.addPicture(address);
    }
}
