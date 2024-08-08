package com.ghostface.dev.application.impl.thread;

import com.ghostface.dev.application.impl.UserImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ScreeningThread extends Thread {

    private final @NotNull JChatServerThread chatClient;
    private @Nullable Thread thread;

    public ScreeningThread(@NotNull JChatServerThread chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(chatClient.getSocket().getInputStream()));
             PrintWriter writer = new PrintWriter(chatClient.getSocket().getOutputStream(), true)) {

            @NotNull String username = reader.readLine();
            @NotNull UserImpl user = new UserImpl(username);

            if (!chatClient.addUser(user)) {
                writer.println(false);
                chatClient.getSocket().close();
            } else {
                writer.println(user.getUsername() + " joined");
            }


        } catch (IOException e) {
            System.err.println(chatClient.getSocket().getInetAddress().getHostAddress() + " Connection lost");
            e.printStackTrace();
        }
    }
}
