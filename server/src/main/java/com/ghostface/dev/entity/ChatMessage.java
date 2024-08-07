package com.ghostface.dev.entity;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public interface ChatMessage {

    @NotNull String getContent();

    @NotNull OffsetDateTime getTime();

    @NotNull
    ChatUser getUser();

}
