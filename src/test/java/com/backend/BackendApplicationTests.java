package com.backend;

import com.backend.entity.Comment;
import com.backend.entity.Message;
import com.backend.entity.User;
import com.backend.repository.jpa.UserRepository;
import com.backend.repository.mongo.CommentRepository;
import com.backend.repository.mongo.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testUser() throws Exception {
        System.out.println("CacheManager type: " + cacheManager.getClass());

        userRepository.save(new User(1, "test", "123"));

        User u1 = userRepository.findByName("test");
        System.out.println("first check:" + u1.getPassword());

        User u2 = userRepository.findByName("test");
        System.out.println("second check:" + u2.getPassword());
    }

    @Test
    public void testMessage() throws Exception {
        List<Message> messages_before = messageRepository.findByAuthor(1);

        messageRepository.save(new Message(1, 1, "test message1", new Date()));
        messageRepository.save(new Message(2, 1, "test message2", new Date()));

        List<Message> messages_after = messageRepository.findByAuthor(1);
//        Assert.assertEquals(messages_before.size() + 2, messages_after.size());
    }

    @Test
    public void testComment() throws Exception {
        commentRepository.save(new Comment(1, 100, "test comment1", new Date(), 100));
        commentRepository.save(new Comment(2, 100, "test comment2", new Date(), 100));

        List<Comment> comments = commentRepository.findCommentsByMessage(100);

        Assert.assertEquals(2, comments.size());
    }

}
