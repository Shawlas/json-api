package com.ghostface.dev.application;

import com.ghostface.dev.thread.Receive;
import com.ghostface.dev.thread.Send;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Lobby implements Runnable {

    private final @NotNull Socket socket;

    public Lobby(int port) {
        try {
            this.socket = new Socket("localhost",port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            @NotNull PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("Enter the username: ");
            @NotNull String username = reader.readLine();
            writer.println(username);
            System.out.println("Welcome " + username);
            new Thread(new Send(socket)).start();
            new Thread(new Receive(socket)).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull Socket getSocket() {
        return socket;
    }
}
