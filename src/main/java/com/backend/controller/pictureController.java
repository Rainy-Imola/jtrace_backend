package com.backend.controller;

import com.backend.entity.Picture;
import com.backend.service.PictureService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/picture")
public class pictureController {
    @Autowired
    PictureService pictureService;

    @GetMapping("/")
    public Picture getPicture(@RequestBody JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        return pictureService.findById(id);
    }

    @PostMapping("/")
    public void postPicture(@RequestBody JSONObject jsonObject) {
        String address = jsonObject.getString("address");
        pictureService.addPicture(address);
    }
}
