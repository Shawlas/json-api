package com.ghostface.dev.thread;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

public class Send implements Runnable {

    private final @NotNull Socket socket;

    public Send(@NotNull Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                @NotNull PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
                @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                @NotNull String message = reader.readLine();
                writer.println(message);
                System.out.println("Message send");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
