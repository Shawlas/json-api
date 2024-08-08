package com.ghostface.dev.application;

import com.ghostface.dev.application.entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JChatServer {

    private @Nullable ServerSocket server;
    private @Nullable JChatClient client;

    public JChatServer() {
    }

    public @Nullable ServerSocket getServer() {
        return server;
    }

    public synchronized void start() {
        try {
            @Nullable ServerSocket server = getServer();

            if (server != null && server.isBound()) {
                throw new IllegalArgumentException("Servers is already active");
            }

            this.server = new ServerSocket(5551, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("server is running");

            @NotNull Socket socket = this.server.accept();
            this.client = new JChatClient(socket);
            this.client.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
