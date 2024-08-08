package com.ghostface.dev.application;

import com.ghostface.dev.application.thread.ScreeningThread;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JChatClient {

    private final @NotNull Socket client;
    private @Nullable Thread thread;

    public JChatClient(@NotNull Socket client) {
        this.client = client;
    }

    public void accept() {
        this.thread = new ScreeningThread(client);
        thread.start();
    }

}
