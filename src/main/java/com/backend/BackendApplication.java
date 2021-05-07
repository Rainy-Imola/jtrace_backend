package com.backend;

import com.backend.websocket.ChatSocketServer;
import org.java_websocket.server.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);

        String host = "localhost";
        Integer port = 8887;

        WebSocketServer server = new ChatSocketServer(new InetSocketAddress(host, port));
        server.run();
    }

}
