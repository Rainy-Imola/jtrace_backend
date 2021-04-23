package com.backend.service;

import com.backend.entity.Picture;

public interface PictureService {
    Picture findById(Integer id);
    Integer addPicture(String address);
}
