package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import com.backend.utils.sessionUtils.SessionUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Login
    @PostMapping("/login")
    public Msg login(@RequestBody JSONObject object) {
        String username = object.getString("username");
        String password = object.getString("password");

        User auth = userService.checkUser(username, password);

        Logger logger = Logger.getLogger(UserController.class);

        if (auth != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", auth.getId());
            jsonObject.put("username", auth.getUsername());
            SessionUtils.setSession(jsonObject);

            List<User> array = new ArrayList<>();
            array.add(auth);

            JSONArray data = JSONArray.fromObject(array);
            logger.info("Path: /login, status: success, username: " + username);
            return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.LOGIN_SUCCESS_MSG, data);
        } else {
            logger.error("Path: /login, status: fail, username: " + username + " password: " + password);
            return MsgUtils.makeMsg(MsgUtils.LOGIN_USER_ERROR, MsgUtils.LOGIN_USER_ERROR_MSG);
        }
    }

    // Get all users
    @GetMapping("/getUsers")
    public List<User> getUsers() { return userService.getUsers(); }

    // Register
    @PostMapping("/register")
    public Msg register(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        User auth = userService.findByName(username);

        Logger logger = Logger.getLogger(UserController.class);

        if (auth == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            userService.addUser(user);

            logger.info("Path: /register, status: success, username: " + username);
            return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.REGISTER_SUCCESS_MSG);
        }  else {
            logger.error("Path: /register, status: fail, username: " + username);
            return MsgUtils.makeMsg(MsgUtils.REGISTER_ERROR, MsgUtils.REGISTER_ERROR_MSG);
        }
    }

    // Get user profile
    @GetMapping("/{username}/info")
    public Msg userInfo(@PathVariable String username) {
        User user = userService.findByName(username);

        List<User> array = new ArrayList<>();
        array.add(user);
        JSONArray data = JSONArray.fromObject(array);

        Logger logger = Logger.getLogger(UserController.class);
        logger.info("Path: /" + username + "/info, status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG, data);
    }

    // Update password
    @PostMapping("/{username}/password")
    public Msg updatePassword(@PathVariable String username, @RequestBody JSONObject jsonObject) {
        User user = userService.findByName(username);
        String password = jsonObject.getString("password");

        user.setPassword(password);
        userService.addUser(user);

        Logger logger = Logger.getLogger(UserController.class);
        logger.info("Path: /" + username + "/password, status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }

    // Update information
    @PostMapping("/{username}/info")
    public Msg updateInfo(@PathVariable String username, @RequestBody JSONObject jsonObject) {
        User user = userService.findByName(username);
        List<String> hobby = (List<String>) jsonObject.get("hobby");
        String constellation = jsonObject.getString("constellation");

        user.setHobby(hobby);
        user.setConstellation(constellation);
        userService.addUser(user);

        Logger logger = Logger.getLogger(UserController.class);
        logger.info("Path: /" + username + "/info, status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
