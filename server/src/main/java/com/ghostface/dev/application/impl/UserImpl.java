package com.ghostface.dev.application.impl;

import com.ghostface.dev.application.entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.Objects;

public final class UserImpl implements User {

    private final @NotNull String username;
    private final @NotNull OffsetDateTime date;

    public UserImpl(@NotNull String username) {
        this.username = username;
        this.date = OffsetDateTime.now();
    }

    @Override
    public @NotNull String getUsername() {
        return username;
    }

    @Override
    public @NotNull OffsetDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull UserImpl userImpl = (UserImpl) object;
        return Objects.equals(username, userImpl.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
