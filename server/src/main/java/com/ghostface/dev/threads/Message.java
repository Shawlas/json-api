package com.ghostface.dev.threads;

import com.ghostface.dev.application.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

final class Message {
    private final @Nullable String msg;
    private final @NotNull User user;
    private final @NotNull OffsetDateTime date;

    public Message(@NotNull User user, @Nullable String msg) {
        this.user = user;
        this.msg = msg;
        this.date = OffsetDateTime.parse(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")));
    }

    public @Nullable String getMsg() {
        return msg;
    }

    public @NotNull User getUser() {
        return user;
    }

    public @NotNull OffsetDateTime getDate() {
        return date;
    }

}
