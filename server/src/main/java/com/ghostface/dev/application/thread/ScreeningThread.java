package com.ghostface.dev.application.thread;

import com.ghostface.dev.application.entity.User;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ScreeningThread extends Thread {

    private final @NotNull Socket socket;

    public ScreeningThread(@NotNull Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Waiting username input of the client");
            @NotNull String user = reader.readLine();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
