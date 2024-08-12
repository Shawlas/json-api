package com.ghostface.dev.connection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.ServerSocket;

public interface Server {

    @Nullable ServerSocket getServer();

    boolean start() throws Exception;

    boolean stop() throws Exception;

}
