package com.backend.daoImpl;

import com.backend.dao.ChatDao;
import com.backend.repository.mongo.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDaoImpl implements ChatDao {
    @Autowired
    private ChatRepository chatRepository;
}
