package com.ghostface.dev.application;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {

    private final @NotNull Username username;
    private final @NotNull OffsetDateTime dateCreation;
    private final @NotNull Socket socket;

    public User(@NotNull Username username, @NotNull Socket socket) {
        this.username = username;
        this.socket = socket;
        this.dateCreation = OffsetDateTime.parse(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public @NotNull Username getUsername() {
        return username;
    }

    public @NotNull OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
