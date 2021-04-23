package com.backend.dao;

import com.backend.entity.Picture;

public interface PictureDao {
    Picture findById(Integer id);
    Integer addPicture(String address);
}
