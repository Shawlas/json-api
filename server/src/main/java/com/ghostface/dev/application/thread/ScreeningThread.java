package com.ghostface.dev.application.thread;

import com.ghostface.dev.application.ChatService;
import com.ghostface.dev.application.impl.SimpleUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.Socket;

/*
* This thread is reponsible to check and create new users
* */
public class ScreeningThread extends Thread {

    private final @NotNull Socket client;
    private final @NotNull ChatService chat;
    private @Nullable Thread thread;

    public ScreeningThread(@NotNull Socket client, @NotNull ChatService chat) {
        this.client = client;
        this.chat = chat;
    }

    // todo username already exist

    @Override
    public void run() {
        try {
            @NotNull PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer.print("Enter the username: ");

            @NotNull String username = reader.readLine();
            @NotNull SimpleUser user = new SimpleUser(username, client);

            if (chat.getUsers().add(user)) {
                writer.println("Welcome " + user.getUsername());

                StringBuilder joinedMessage = new StringBuilder();
                for (@NotNull SimpleUser simpleUser : chat.getUsers()) {
                    if (simpleUser != user) {
                        joinedMessage.append(user.getUsername()).append(" Joined\n");
                    }
                }
                writer.println(joinedMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
