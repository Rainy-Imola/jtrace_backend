package com.backend.repository;

import com.backend.entity.Message;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = "from Message where author = :author")
    List<Message> findByAuthor(@Param("author") User author);

    @Query("select m from Message m")
    List<Message> getMessages();


}
