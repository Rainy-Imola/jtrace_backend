package com.backend.component;

import com.alibaba.fastjson.JSON;
import com.backend.entity.Chat;
import com.backend.service.ChatService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/{username}")
@Component
public class WebSocketServerComponent {

    @Autowired
    private ChatService chatService;

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
        sessionPools.put(username, session);
        addOnlineCount();
        System.out.println("Current online user number is: " + onlineNum);
    }

    // Close a connection
    @OnClose
    public void onClose(@PathParam(value = "username") String username) {
        sessionPools.remove(username);
        subOnlineCount();
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

        // chatService.addChat(chat);
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
