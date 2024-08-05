package com.ghostface.dev.threads;

import com.ghostface.dev.application.User;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class Insertion implements Runnable {

    private final @NotNull Set<@NotNull User> usersConnected;
    private final @NotNull Socket socket;
    private @Nullable Message msg;

    public Insertion(@NotNull Set<@NotNull User> usersConnected, @NotNull Socket socket) {
        this.usersConnected = usersConnected;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                @NotNull PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(usersConnected);
                for (@NotNull User user : usersConnected) {
                    System.out.println("Loading message...");
                    @Nullable String message = reader.readLine();
                    System.out.println("Message load.");
                    msg = new Message(user, message);
                    writer.println(msg.getDateString() + " - " + msg.getUser().getUsername().getString() + ": " + msg.getMsg());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
