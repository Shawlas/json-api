package com.ghostface.dev.factory;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public interface User {

    @NotNull String getUsername();

    @NotNull OffsetDateTime getDate();

}
