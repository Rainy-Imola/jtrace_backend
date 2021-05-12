package com.backend;

import com.backend.entity.User;
import com.backend.repository.jpa.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void test() throws Exception {
        userRepository.save(new User(1, "test", "123"));

        User u1 = userRepository.findByName("test");
        System.out.println("first check:" + u1.getPassword());

        User u2 = userRepository.findByName("test");
        System.out.println("second check:" + u2.getPassword());
    }
}
