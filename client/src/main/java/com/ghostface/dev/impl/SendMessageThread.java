package com.ghostface.dev.impl;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

final class SendMessageThread extends Thread {

    private final @NotNull Socket socket;

    public SendMessageThread(@NotNull Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try (@NotNull PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                @NotNull BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

                @NotNull String msg = input.readLine();

                writer.println(msg);

                @NotNull Thread thread = new GetMessageThread();

                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public @NotNull Socket getSocket() {
        return socket;
    }

    final class GetMessageThread extends Thread {

        @Override
        public void run() {
            try (@NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()))) {

                @NotNull String msg = reader.readLine();

                System.out.println(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
