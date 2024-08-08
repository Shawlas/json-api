package com.ghostface.dev;

import com.ghostface.dev.application.JChatServer;

public class ServerApplication {
    public static void main(String[] args) {
        JChatServer server = new JChatServer();
        server.start();
    }
}
