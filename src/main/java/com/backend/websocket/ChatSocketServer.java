package com.backend.websocket;

import com.backend.entity.Chat;
import com.backend.service.ChatService;
import net.sf.json.JSONObject;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatSocketServer extends WebSocketServer {

    public ChatSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Autowired
    private ChatService chatService;

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("Welcome to server!");
        System.out.println("new connection to " + webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean b) {
        System.out.println("closed " + webSocket.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        JSONObject jsonObject = JSONObject.fromObject(message);
        String msg = jsonObject.getString("message");
        String from = jsonObject.getString("from");
        String to = jsonObject.getString("to");
        Date date = new Date();
        Boolean read = false;

        Chat chat = new Chat();
        chat.setFrom(from);
        chat.setTo(to);
        chat.setMessage(msg);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        chat.setTime(time);
        chat.setRead(read);

        chatService.addChat(chat);

        System.out.println("received message from " + webSocket.getRemoteSocketAddress() + ": " + message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.err.println("an error occurred on connection " + webSocket.getRemoteSocketAddress() + ": " + e);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }

    public void sendMessage(String IPAddress, String message) {

    }

}
