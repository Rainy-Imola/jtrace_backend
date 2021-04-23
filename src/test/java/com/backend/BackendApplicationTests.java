package com.backend;

import com.backend.entity.User;
import com.backend.service.MessageService;
import com.backend.service.PictureService;
import com.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BackendApplicationTests {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    PictureService pictureService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void userTest() throws Exception {
    }

    @Test
    public void messageTest() throws Exception {
    }

    @Test
    public void pictureTest() throws Exception {
    }
}
