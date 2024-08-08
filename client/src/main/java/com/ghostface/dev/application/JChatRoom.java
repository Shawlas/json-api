package com.ghostface.dev.application;

import com.ghostface.dev.application.thread.ClientScreeningThread;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.Socket;

public class JChatRoom {

    private @Nullable Socket socket;

    public JChatRoom() {
    }

    public void join() {
        try {
            this.socket = new Socket("localhost", 5551);
            @NotNull Thread thread = new ClientScreeningThread(socket);

            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
