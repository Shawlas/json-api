package com.ghostface.dev.application;

import com.ghostface.dev.client.User;
import com.ghostface.dev.thread.DataConnection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoom {

    private final @NotNull ServerSocket server;
    private final @NotNull Set<@NotNull User> usersConnected;
    private @Nullable Socket client;

    public ChatRoom() {
        try {
            @NotNull InetAddress inet = InetAddress.getByName("0.0.0.0");
            this.server = new ServerSocket(5551, 50, inet);
            this.usersConnected = ConcurrentHashMap.newKeySet();
            System.out.println("Server started");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull ServerSocket getServer() {
        return server;
    }

    // todo username already exit
    public void start() {
        for (int i = 1; i < 10; i++) {
            try {
                this.client = server.accept();
                @NotNull DataConnection connection = new DataConnection(client);
                @NotNull Thread thread = new Thread(connection);
                thread.start();
                usersConnected.add(connection.getUser());
                thread.join();

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public @Nullable Socket getClient() {
        return client;
    }
}
