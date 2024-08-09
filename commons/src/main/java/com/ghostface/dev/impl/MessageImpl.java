package com.ghostface.dev.impl;

import com.ghostface.dev.application.entity.Message;
import com.ghostface.dev.application.entity.User;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public final class MessageImpl implements Message {
    private final @NotNull UserImpl user;
    private final @NotNull String content;
    private final @NotNull OffsetDateTime time;

    public MessageImpl(@NotNull UserImpl user, @NotNull String content) {
        this.user = user;
        this.content = content;
        this.time = OffsetDateTime.now();
    }

    @Override
    public @NotNull User getUser() {
        return user;
    }

    @Override
    public @NotNull String getContent() {
        return content;
    }

    @Override
    public @NotNull OffsetDateTime getDate() {
        return time;
    }

}
