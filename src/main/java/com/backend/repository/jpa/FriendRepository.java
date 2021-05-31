package com.backend.repository.jpa;

import com.backend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query(value = "from Friend where username1 = :username order by username2 asc")
    List<Friend> findByUsername1(String username);

    Friend findByUsername1AndUsername2(String username1, String username2);

    void deleteFriendByUsername1AndUsername2(String username1, String username2);
}
