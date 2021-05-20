package com.backend;

import com.backend.entity.Chat;
import com.backend.entity.Comment;
import com.backend.entity.Message;
import com.backend.entity.User;
import com.backend.repository.jpa.UserRepository;
import com.backend.repository.mongo.ChatRepository;
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
    private ChatRepository chatRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testUserRepo() throws Exception {
        System.out.println("CacheManager type: " + cacheManager.getClass());

        // test getUsers
        List<User> users_before = userRepository.getUsers();
        userRepository.save(new User(4, "test user1", "123"));
        userRepository.save(new User(5, "test user2", "123"));
        List<User> users_after = userRepository.getUsers();
        Assert.assertEquals(users_before.size() + 2, users_after.size());

        // test checkUser
        User user1 = userRepository.checkUser("test user1", "123");
        Assert.assertNotNull(user1);
        User user2 = userRepository.checkUser("test checkUser", "password");
        Assert.assertNull(user2);

        // test findByName
        User user3 = userRepository.findByName("test user1");
        Assert.assertNotNull(user3);
        User user4 = userRepository.findByName("test checkByName");
        Assert.assertNull(user4);

        // test findByEmail
        String email = "12345@sjtu.edu.cn";
        user3.setEmail(email);
        userRepository.save(user3);
        User user5 = userRepository.findByEmail(email);
        Assert.assertNotNull(user5);
        User user6 = userRepository.findByEmail("123@sjtu.edu.cn");
        Assert.assertNull(user6);

        userRepository.delete(userRepository.findByName("test user1"));
        userRepository.delete(userRepository.findByName("test user2"));
    }

    @Test
    public void testMessageRepo() throws Exception {
        // test findByAuthor
        List<Message> messages_before = messageRepository.findByAuthor(1);
        messageRepository.save(new Message(1, 1, "test message1", new Date(), 0));
        messageRepository.save(new Message(2, 1, "test message2", new Date(), 0));
        List<Message> messages_after = messageRepository.findByAuthor(1);

        Assert.assertEquals(messages_before.size() + 2, messages_after.size());

        messageRepository.delete(messageRepository.findById(1).get());
        messageRepository.delete(messageRepository.findById(2).get());
    }

    @Test
    public void testCommentRepo() throws Exception {
        // test findCommentByMessage
        List<Comment> comments_before = commentRepository.findCommentsByMessage(100);
        Comment comment1 = new Comment(1, 100, "test comment1", new Date(), 100);
        Comment comment2 = new Comment(2, 100, "test comment1", new Date(), 100);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        List<Comment> comments_after = commentRepository.findCommentsByMessage(100);
        Assert.assertEquals(comments_before.size() + 2, comments_after.size());

        commentRepository.delete(comment1);
        commentRepository.delete(comment2);
    }

    @Test
    public void testChatRepo() throws Exception {
        // test getChatByFromAndTo
        List<Chat> chats_before = chatRepository.getChatsByFromAndTo("test chatFrom", "test chatTo");
        Chat chat1 = new Chat(1, "test chatFrom", "test chatTo", "test getChatByFromAndTo", "2021-5-20");
        chatRepository.save(chat1);
        List<Chat> chats_after = chatRepository.getChatsByFromAndTo("test chatFrom", "test chatTo");
        Assert.assertEquals(chats_before.size() + 1, chats_after.size());

        chatRepository.delete(chat1);
    }

}
