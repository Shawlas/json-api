package com.ghostface.dev.application.entity;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.time.OffsetDateTime;

public final class User {

    private final @NotNull String username;
    private final @NotNull Socket socket;
    private final @NotNull OffsetDateTime date;

    public User(@NotNull String username, @NotNull Socket socket) {
        this.username = username;
        this.socket = socket;
        this.date = OffsetDateTime.now();
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @NotNull Socket getSocket() {
        return socket;
    }

    public @NotNull OffsetDateTime getDate() {
        return date;
    }
}
