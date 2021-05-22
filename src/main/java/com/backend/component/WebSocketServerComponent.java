package com.backend.component;

import com.alibaba.fastjson.JSON;
import com.backend.entity.Chat;
import com.backend.entity.User;
import com.backend.service.ChatService;
import com.backend.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/webSocket/{username}")
public class WebSocketServerComponent {

    private static WebApplicationContext context;
    @Autowired
    public void getContext(WebApplicationContext context) {
        this.context = context;
    }

    private static AtomicInteger onlineNum = new AtomicInteger(); // record current online number
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>(); // store websocketServer for every client

    // send message
    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                System.out.println("Send message: " + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    // send message to specific user
    public void sendInfo(String username, String message) {
        Session session = sessionPools.get(username);
        if (session == null) {
            System.out.println("Send message error: User " + username + " is not online");
            return;
        }

        try {
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Open a connection
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username) {
        Session sessionBefore = sessionPools.get(username);
        if (sessionBefore != null) {
            try {
                sessionBefore.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sessionPools.put(username, session);
        addOnlineCount();

        UserService userService = context.getBean(UserService.class);
        User user = userService.findByName(username);
        user.setStatus(true);
        userService.addUser(user);

        System.out.println("Current online user number is: " + onlineNum);
    }

    // Close a connection
    @OnClose
    public void onClose(@PathParam(value = "username") String username) throws IOException {
        sessionPools.remove(username);
        subOnlineCount();

        UserService userService = context.getBean(UserService.class);
        User user = userService.findByName(username);
        user.setStatus(false);
        userService.addUser(user);

        System.out.println("Current online number is :" + onlineNum);
    }

    // Send message to specific user after receiving a message
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("Server get: " + message);
        JSONObject jsonObject = JSONObject.fromObject(message);
        Chat chat = new Chat();
        chat.setFrom(jsonObject.getString("From"));
        chat.setTo(jsonObject.getString("To"));
        chat.setMessage(jsonObject.getString("message"));
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        chat.setTime(time);

        ChatService chatService = context.getBean(ChatService.class);

        chatService.addChat(chat);
        sendInfo(chat.getTo(), JSON.toJSONString(chat, true));
    }

    // Error
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error!!!");
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

    public static AtomicInteger getOnlineNumber() {
        return onlineNum;
    }

    public static ConcurrentHashMap<String, Session> getSessionPools() {
        return sessionPools;
    }
}
