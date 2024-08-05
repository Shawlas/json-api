package com.ghostface.dev.application.screening;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {

    private final @NotNull String username;
    private final @NotNull OffsetDateTime dateCreation;

    public User(@NotNull String username, @NotNull OffsetDateTime dateCreation) {
        this.username = username;
        this.dateCreation = OffsetDateTime.parse(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @NotNull OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull User user = (User) object;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
