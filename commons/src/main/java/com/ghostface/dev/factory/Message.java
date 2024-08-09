package com.ghostface.dev.factory;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public interface Message extends CharSequence {

    @NotNull User getUser();

    @NotNull String getContent();

    @NotNull OffsetDateTime getDate();

    default @NotNull String getFormated() {
        @NotNull String time = getDate().toString().subSequence(0, 10) + " " + getDate().toString().subSequence(11, 16);
        return "[" + time + "] " + getUser().getUsername() + ": " + getContent();
    }

    @Override
    default int length() {
        return toString().length();
    }

    @Override
    default char charAt(int index) {
        return toString().charAt(index);
    }

    @NotNull
    @Override
    default CharSequence subSequence(int start, int end) {
        return toString().subSequence(start,end);
    }

}
