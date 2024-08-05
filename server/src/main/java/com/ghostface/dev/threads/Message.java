package com.ghostface.dev.threads;

import com.ghostface.dev.application.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

final class Message implements CharSequence {
    private final @Nullable String msg;
    private final @NotNull User user;
    private final @NotNull OffsetDateTime date;

    public Message(@NotNull User user, @Nullable String msg) {
        this.user = user;
        this.msg = msg;
        this.date = OffsetDateTime.now();
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

    public @NotNull String getDateString() {
        @NotNull String date = (String) getDate().toString().subSequence(0,9);
        @NotNull String hour = (String) getDate().toString().subSequence(11, 16);
        return date + " " + hour;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }


    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }
}
