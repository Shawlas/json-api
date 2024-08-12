package com.ghostface.dev.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

final class GetMessageThread extends Thread {

    private final @NotNull Socket socket;
    private final @NotNull JChatServer chat;
    private @Nullable MessageImpl message;

    public GetMessageThread(@NotNull Socket socket, @NotNull JChatServer chat) {
        this.socket = socket;
        @Nullable ServerSocket server = chat.getServer();

        if (server == null) {
            throw new IllegalArgumentException("Server is not running");
        }

        this.chat = chat;
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try (@NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                @NotNull String message = reader.readLine();

                @Nullable UserImpl user = chat.getUsers().stream().filter(user1 -> user1.getSocket().equals(socket)).findFirst().orElse(null);

                System.out.println(user);

                if (user == null) {
                    throw new IllegalArgumentException("This socket user is not authenticated");
                }

                this.message = new MessageImpl(user, message, Instant.now());

                @NotNull Thread thread = new SendMensageThread();

                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public @Nullable MessageImpl getMessage() {
        return message;
    }

    final class SendMensageThread extends Thread {

        @Override
        public void run() {
            @Nullable MessageImpl msg = getMessage();

            if (msg  != null) {
                for (@NotNull UserImpl user : chat.getUsers()) {
                    try {
                        @NotNull PrintWriter writer = new PrintWriter(user.getSocket().getOutputStream(), true);
                        @NotNull OffsetDateTime time = OffsetDateTime.parse(msg.getDate().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")));
                        writer.println("[" + time + "] " + msg.getUser().getUsername() + ": " + msg.getContent());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
