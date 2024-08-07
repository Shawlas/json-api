package com.ghostface.dev.application.impl;

import com.ghostface.dev.entity.ChatMessage;
import com.ghostface.dev.entity.ChatUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SimpleMessage implements ChatMessage {

    private final @NotNull String content;
    private final @NotNull OffsetDateTime time;
    private final @NotNull SimpleUser user;

    public SimpleMessage(@NotNull SimpleUser user, @NotNull String content) {
        this.user = user;
        this.content = content;
        this.time = OffsetDateTime.now();
    }

    public @NotNull String getFormated() {
        return String.valueOf("[" + time.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")) + "] " + user.getUsername() + ": " + content);
    }

    @Override
    public @NotNull String getContent() {
        return content;
    }

    @Override
    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    @Override
    public @NotNull ChatUser getUser() {
        return user;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull SimpleMessage that = (SimpleMessage) object;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }
}
