package com.ghostface.dev.application;

import com.ghostface.dev.application.thread.ScreeningThread;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ChatServiceThread extends Thread {

    private final @NotNull ChatService chat;
    private final @NotNull ServerSocket server;
    private @Nullable Thread thread;

    public ChatServiceThread(@NotNull ChatService chat) {
        this.chat = chat;
        @Nullable ServerSocket server = getChat().getServer();

        if (server == null) {
            throw new IllegalArgumentException("Chat server is not active");
        }

        this.server = server;
    }

    public @NotNull ChatService getChat() {
        return chat;
    }

    @Override
    public void run() {
        try {
            while (server.isBound()) {
                System.out.println("Servers started");
                @NotNull Socket client = server.accept();
                this.thread = new ScreeningThread(client, this.chat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
