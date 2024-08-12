package com.ghostface.dev.factory;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;

public interface User {

    @NotNull String getUsername();

    long getCreation();

}
