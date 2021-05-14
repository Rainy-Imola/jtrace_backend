package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import com.backend.utils.msgUtils.Msg;
import com.backend.utils.msgUtils.MsgUtils;
import com.backend.utils.sessionUtils.SessionUtils;

import com.louislivi.fastdep.shirojwt.jwt.JwtUtil;

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

    @Autowired
    private JwtUtil jwtUtil;

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

            auth.setStatus(true);
            userService.addUser(auth);

            List<User> array = new ArrayList<>();
            array.add(auth);
            JSONArray data = JSONArray.fromObject(array);
            JSONObject tokenJson = new JSONObject();
            tokenJson.put("token", jwtUtil.sign(username));
            data.add(tokenJson);

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
        String password1 = jsonObject.getString("password1");
        String password2 = jsonObject.getString("password2");
        String email = jsonObject.getString("email");

        Logger logger = Logger.getLogger(UserController.class);

        if (!password1.equals(password2)) {
            logger.error("Path: /users/register, status: fail, error msg: password1 != password2");
            return MsgUtils.makeMsg(MsgUtils.ERROR, MsgUtils.ERROR_MSG);
        }

        User user0 = userService.findByEmail(email);
        if (user0 != null) {
            logger.error("Path: /register, status: fail, username: " + username + " Error: email has been taken");
            return MsgUtils.makeMsg(MsgUtils.REGISTER_ERROR, MsgUtils.REGISTER_ERROR_MSG);
        }


        User auth = userService.findByName(username);

        if (auth == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password1);
            user.setEmail(email);
            user.setStatus(true);

            List<User> array = new ArrayList<>();
            array.add(user);
            JSONArray data = JSONArray.fromObject(array);
            JSONObject tokenJson = new JSONObject();
            tokenJson.put("token", jwtUtil.sign(username));
            data.add(tokenJson);

            userService.addUser(user);

            logger.info("Path: /register, status: success, username: " + username);
            return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.REGISTER_SUCCESS_MSG, data);
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

    // Update avatar
    @PostMapping("/{username}/avatar")
    public Msg updateAvatar(@PathVariable String username, @RequestBody JSONObject jsonObject) {
        User user = userService.findByName(username);
        String avatar = (String) jsonObject.get("avatar");

        user.setAvatar(avatar);
        userService.addUser(user);

        Logger logger = Logger.getLogger(UserController.class);
        logger.info("Path: /" + username + "/avatar, status: success");
        return MsgUtils.makeMsg(MsgUtils.SUCCESS, MsgUtils.SUCCESS_MSG);
    }
}
