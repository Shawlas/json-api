package com.ghostface.dev.application.thread;


import com.ghostface.dev.application.entity.User;
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
        synchronized (this) {
            try {
                @NotNull PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                @NotNull BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Enter the username: ");
                @NotNull String username = input.readLine();

                if (Username.validate(username)) {
                    @NotNull User user = new User(username,socket);
                    writer.println(user);
                }

                writer.println(username);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
