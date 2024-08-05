package com.ghostface.dev.thread;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {

    private final @NotNull Socket socket;

    public Receive(@NotNull Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                @NotNull String message = reader.readLine();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
