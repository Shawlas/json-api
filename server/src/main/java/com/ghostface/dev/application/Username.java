package com.ghostface.dev.application;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

public class Username implements CharSequence {

    private final @NotNull String username;

    public Username(@NotNull String username) {
        if (!validate(username)) {
            throw new IllegalArgumentException("username invalid");
        }
        this.username = username;
    }

    public static boolean validate(final @Nullable String username) {
        if (Character.isDigit(username.charAt(0))) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9_-]{4,16}$");
    }

    public static boolean contains(@NotNull Set<@NotNull User> users, @NotNull String username) {
        return users.stream().anyMatch(user -> user.getUsername().getString().equalsIgnoreCase(username));
    }

    // getters


    public @NotNull String getString() {
        return username;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Username username1 = (Username) object;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
