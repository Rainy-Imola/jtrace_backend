package com.backend.controller;

import com.backend.dao.UserDao;
import com.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    // create user
    @PostMapping("/")
    public String postUser(@RequestBody User user) {
        User newUser = new User(user.getId(), user.getUsername(), user.getPassword());
        userDao.save(newUser);
        return "success";
    }

    // get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        return users;
    }

    // get user
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userDao.findById(id).get();
        return user;
    }

    // update password
    @PutMapping("/{id}")
    public User updatePassword(@PathVariable Integer id, @RequestBody User usr) {
        User user = userDao.findById(id).get();
        user.setPassword(usr.getPassword());
        userDao.save(user);
        return user;
    }
}
