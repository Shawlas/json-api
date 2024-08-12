package com.ghostface.dev.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class JChatServerThread extends Thread {

    private final @NotNull JChatServer chat;
    private final @NotNull ServerSocket server;
    private @Nullable Socket client;

    public JChatServerThread(@NotNull JChatServer chat) {
        this.chat = chat;

        @Nullable ServerSocket server = chat.getServer();

        if (server == null) {
            throw new IllegalArgumentException("Server is not running");
        }

        this.server = server;
    }

    @Override
    public void run() {
        while (server.isBound()) {
            try {
                this.client = server.accept();
                System.out.println(client.getLocalAddress().getHostAddress() + " Trying to connect");

                @NotNull Thread screening = new ScreeningThread(this);
                @NotNull Thread msg = new GetMessageThread(client, chat);

                screening.start();
                msg.start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public @Nullable Socket getClient() {
        return client;
    }

    static final class ScreeningThread extends Thread {

        private final @NotNull Socket socket;
        private final @NotNull JChatServerThread thread;

        public ScreeningThread(@NotNull JChatServerThread thread) {
            this.thread = thread;
            @Nullable Socket client = thread.getClient();

            if (client == null || !client.isConnected()) {
                throw new IllegalArgumentException("Client aren´t active");
            }

            this.socket = thread.getClient();

        }

        @Override
        public synchronized void run() {
            try (@NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 @NotNull PrintWriter writer = new PrintWriter(socket.getOutputStream(), true))
            {
                System.out.println("starting authentication to " + socket.getLocalAddress().getHostAddress());

                // get username string from client
                @NotNull String username = reader.readLine();
                @NotNull UserImpl user = new UserImpl(username, socket);

                if (!thread.chat.getUsers().add(user)) {
                    System.out.println();
                    writer.println(false);
                    System.out.println("unsuccessful authentication of " + socket.getLocalAddress().getHostAddress());
                    socket.close();
                } else {
                    writer.println(true);
                    System.out.println("successful authentication of " + socket.getLocalAddress().getHostAddress());
                    System.out.println(user.getUsername() + " joined");
                }

            } catch (SocketException ignore) {
                System.out.println(socket.getLocalAddress().getHostAddress() + " Connection lost");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
