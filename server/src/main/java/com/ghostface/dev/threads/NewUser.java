package com.ghostface.dev.threads;

import com.ghostface.dev.application.User;
import com.ghostface.dev.application.Username;
import com.ghostface.dev.utilities.Reader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Socket;

public class NewUser implements Runnable {

    private final @NotNull Socket client;
    private @NotNull User user;

    public NewUser(@NotNull Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        @Nullable String username = Reader.of(client);
        user = new User(new Username(username), client);
        System.out.println(user.getUsername() + " joined");
    }

    public @NotNull User getUser() {
        return user;
    }
}
