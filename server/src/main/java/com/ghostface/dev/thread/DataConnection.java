package com.ghostface.dev.thread;

import com.ghostface.dev.client.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DataConnection implements Runnable {

    private final @NotNull Socket client;
    private @NotNull User user;

    public DataConnection(@NotNull Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Trying connect");
            @NotNull String usernameData = reader.readLine();

            this.user = new User(usernameData, client);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public @Nullable User getUser() {
        return user;
    }
}
