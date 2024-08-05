package com.ghostface.dev.threads;

import com.ghostface.dev.application.User;
import com.ghostface.dev.application.Username;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Set;

public class NewUser implements Runnable {

    private final @NotNull Socket client;
    private @NotNull User user;
    private final @NotNull Set<@NotNull User> usersConnected;


    public NewUser(@NotNull Socket client, @NotNull Set<@NotNull User> usersConnected) {
        this.client = client;
        this.usersConnected = usersConnected;
    }

    @Override
    public void run() {
        try {
            @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            @NotNull String username = reader.readLine();
            user = new User(new Username(username), client);
            usersConnected.add(user);
            System.out.println(user.getUsername().getString() + " joined");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public @NotNull User getUser() {
        return user;
    }

    public @NotNull Set<@NotNull User> getUsersConnected() {
        return usersConnected;
    }
}
