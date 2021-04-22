package com.backend.service;

import com.backend.entity.Picture;

public interface PictureService {
    Picture findById(Integer id);
    void addPicture(String address);
}
