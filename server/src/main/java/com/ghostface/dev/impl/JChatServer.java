package com.ghostface.dev.impl;

import com.ghostface.dev.connection.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JChatServer implements Server {

    // object
    private final @NotNull InetSocketAddress address;

    private final @NotNull Set<@NotNull UserImpl> users = ConcurrentHashMap.newKeySet();
    private @Nullable ServerSocket server;
    private @Nullable Thread thread;

    public JChatServer(@NotNull InetSocketAddress address) {
        this.address = address;
    }

    // loaders

    @Override
    public synchronized boolean start() throws Exception {
        @Nullable ServerSocket server = getServer();

        if (server != null && server.isBound()) {
            return false;
        }

        // socket
        this.server = new ServerSocket(address.getPort(), 50, address.getAddress());
        System.out.println("server is running");

        this.thread = new JChatServerThread(this);
        this.thread.start();

        return true;
    }

    @Override
    public synchronized boolean stop() throws Exception {
        @Nullable ServerSocket server = getServer();

        if (server == null || !server.isBound()) {
            return false;
        }

        // close

        for (@NotNull UserImpl user : getUsers()) {
            user.getSocket().close();
        }

        users.clear();

        this.server = null;

        return true;
    }

    // getters

    @Override
    public @Nullable ServerSocket getServer() {
        return server;
    }

    public @Nullable Thread getThread() {
        return thread;
    }

    public @NotNull Set<@NotNull UserImpl> getUsers() {
        return users;
    }

}
