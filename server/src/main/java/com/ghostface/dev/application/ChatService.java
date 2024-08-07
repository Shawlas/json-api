package com.ghostface.dev.application;

import com.ghostface.dev.application.impl.SimpleUser;
import com.ghostface.dev.application.impl.UsersConnected;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Collection;

public class ChatService {

    private final @NotNull Collection<@NotNull SimpleUser> users = new UsersConnected();
    private @Nullable ServerSocket server;
    private @Nullable Thread thread;

    public ChatService() {
    }

    // getters

    public @Nullable ServerSocket getServer() {
        return server;
    }

    public @NotNull Collection<@NotNull SimpleUser> getUsers() {
        return users;
    }

    // loaders

    public synchronized void start() {
        try {
            this.server = new ServerSocket(5551, 50, InetAddress.getByName("0.0.0.0"));
            this.thread = new ChatServiceThread(this);
            this.thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
