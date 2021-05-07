package com.backend.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class ChatSocketServer extends WebSocketServer {

    public ChatSocketServer(InetSocketAddress address) {
        super(address);
    }

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

}
