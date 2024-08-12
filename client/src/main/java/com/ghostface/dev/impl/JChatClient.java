package com.ghostface.dev.impl;

import com.ghostface.dev.connection.Client;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;
import java.net.Socket;


public final class JChatClient implements Client {

    private final @NotNull InetSocketAddress address;
    private @Nullable Socket socket;

    public JChatClient(@NotNull InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public void join() throws Exception {
        @Nullable Socket socket = getSocket();

        if (socket != null && socket.isConnected()) {
            throw new IllegalArgumentException("Socket already is running");
        }

        this.socket = new Socket(address.getHostName(), address.getPort());
        System.out.println("joined in the server");

        @NotNull Thread screening = new ScreeningThread(this.socket);
        @NotNull Thread sendMsg = new SendMessageThread(this.socket);

        screening.start();
        sendMsg.start();
    }

    // getters

    @Override
    public @Nullable Socket getSocket() {
        return socket;
    }

    public @NotNull InetSocketAddress getAddress() {
        return address;
    }

}
