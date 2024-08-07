package com.ghostface.dev.application.impl;

import com.ghostface.dev.entity.ChatUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Socket;
import java.time.OffsetDateTime;
import java.util.Objects;

public class SimpleUser implements ChatUser {

    private final @NotNull OffsetDateTime time;
    private final @NotNull String username;
    private final @NotNull Socket client;

    public SimpleUser(@NotNull String username, @NotNull Socket client) {
        this.time = OffsetDateTime.now();
        this.username = username;
        this.client = client;
    }

    @NotNull Socket getClient() {
        return client;
    }

    @Override
    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    @Override
    public @NotNull String getUsername() {
        return username;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull SimpleUser that = (SimpleUser) object;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
