package com.ghostface.dev.application;

import com.ghostface.dev.application.thread.ClientScreeningThread;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.Socket;

public class JChatRoom {

    private @Nullable Socket socket;
    private @Nullable Thread thread;

    public JChatRoom() {
    }

    public void join() {
        try {
            this.socket = new Socket("localhost", 5551);
            this.thread = new ClientScreeningThread(socket);
            this.thread.start();
        } catch (IOException e) {

        }
    }
}
