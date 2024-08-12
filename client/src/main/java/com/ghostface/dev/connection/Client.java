package com.ghostface.dev.connection;

import org.jetbrains.annotations.Nullable;

import java.net.Socket;

public interface Client {

    @Nullable Socket getSocket();

    void join() throws Exception;

}
