package com.ghostface.dev.entity;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public interface ChatUser {

    @NotNull OffsetDateTime getTime();

    @NotNull String getUsername();

}
