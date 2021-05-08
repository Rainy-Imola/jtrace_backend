package com.backend.repository.jpa;

import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "from User where username = :username and password = :password")
    User checkUser(@Param("username") String username, @Param("password") String password);

    @Query("select u from User u")
    List<User> getUsers();

    @Query(value = "from User where username = :username")
    User findByName(@Param("username") String username);

    @Query(value = "from User where email = :email")
    User findByEmail(@Param("email") String email);
}
