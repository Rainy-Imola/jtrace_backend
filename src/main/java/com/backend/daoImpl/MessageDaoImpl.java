package com.backend.daoImpl;

import com.backend.dao.MessageDao;
import com.backend.entity.Message;
import com.backend.repository.mongo.MessageRepository;
import com.backend.utils.mongoUtils.MongoAutoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MongoAutoIdUtils mongoAutoIdUtils;

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @Override
    public List<Message> findByAuthor(Integer author) {
        return messageRepository.findByAuthor(author);
    }

    @Override
    public Message releaseMessage(Message message) {
        message.setId(mongoAutoIdUtils.getNextSequence("messsage"));
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message findById(Integer id) {
        if (messageRepository.findById(id).isPresent()) {
            return messageRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Message likeMessage(Integer id) {
        Message message = messageRepository.findById(id).get();

        Integer like = message.getLike();
        like ++;

        message.setLike(like);
        messageRepository.save(message);
        return message;
    }

    @Override
    public Message unlikeMessage(Integer id) {
        Message message = messageRepository.findById(id).get();

        Integer like = message.getLike();
        like --;

        message.setLike(like);
        messageRepository.save(message);
        return message;
    }
}
