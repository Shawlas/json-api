package com.ghostface.dev.impl;

import com.ghostface.dev.factory.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.net.Socket;

import java.util.Objects;

public class UserImpl implements User, Serializable {

    private final @NotNull String username;
    private final long creation;
    private final transient @NotNull Socket socket;

    public UserImpl(@NotNull String username, @NotNull Socket socket) {
        this.username = username;
        this.creation = System.currentTimeMillis();
        this.socket = socket;
    }

    @Override
    public @NotNull String getUsername() {
        return username;
    }

    @Override
    public long getCreation() {
        return creation;
    }

    public @NotNull Socket getSocket() {
        return socket;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull UserImpl user = (UserImpl) object;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
