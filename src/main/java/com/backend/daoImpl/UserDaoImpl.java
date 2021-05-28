package com.backend.daoImpl;

import com.backend.dao.UserDao;
import com.backend.entity.User;
import com.backend.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.checkUser(username, password);
    }

    @Override
    public User addUser(User user) {
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Integer getUserStatus(String username) {
        User user = userRepository.findByName(username);
        if (user.getStatus()) {
            return 1;
        } else {
            return 0;
        }
    }
}
