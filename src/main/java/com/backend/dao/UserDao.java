package com.backend.dao;

import com.backend.entity.User;

import java.util.List;

public interface UserDao {
    User checkUser(String username, String password);
    User addUser(User user);
    List<User> getUsers();
    User findByName(String username);
    User findByEmail(String email);
    User findById(Integer id);
}
