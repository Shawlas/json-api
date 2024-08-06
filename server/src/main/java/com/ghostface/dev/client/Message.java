package com.ghostface.dev.client;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public class Message implements CharSequence {

    private final @NotNull User from;
    private final @NotNull OffsetDateTime time;
    private final @NotNull String content;

    public Message(@NotNull User from, @NotNull String content) {
        this.from = from;
        this.content = content;
        this.time = OffsetDateTime.now();
    }

    public @NotNull User getFrom() {
        return from;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    public @NotNull String getInfo() {
        @NotNull String time = this.time.toString().subSequence(0,10) + " " + this.time.toString().subSequence(11, 16);
        return "[" + time + "] " + this.from.getUsername() + ": " + this.content;
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
        return toString().subSequence(start,end);
    }
}
