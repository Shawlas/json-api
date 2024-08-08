package com.ghostface.dev.application.thread;


import com.ghostface.dev.application.util.Username;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientScreeningThread extends Thread {

    private final @NotNull Socket socket;

    public ClientScreeningThread(@NotNull Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            @NotNull String username;

            do {
                System.out.printf("%nEnter the username: ");
                username = input.readLine();
            } while (!Username.validate(username));

            writer.println(username);

            System.out.println(reader.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
