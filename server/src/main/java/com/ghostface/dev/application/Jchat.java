package com.ghostface.dev.application;

import com.ghostface.dev.threads.Insertion;
import com.ghostface.dev.threads.NewUser;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class Jchat implements Runnable {

    private final @NotNull Set<@NotNull User> usersConnected;
    private final @NotNull ServerSocket server;
    private @NotNull Socket clientSocket;

    public Jchat(int port) {
        try {
            this.server = new ServerSocket(port);
            this.usersConnected = ConcurrentHashMap.newKeySet();
            System.out.println("Servers started");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        new Thread(() -> {
            for (int i = 1; i <10; i++) {
                try {
                    this.clientSocket = server.accept();
                    System.out.println("Servers available");
                    @NotNull NewUser newUser = new NewUser(clientSocket);
                    @NotNull Thread getNewUser = new Thread(newUser);

                    getNewUser.start();
                    getNewUser.join();
                    usersConnected.add(newUser.getUser());

                    new Thread(new Insertion(usersConnected, clientSocket)).start();

                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public @NotNull Socket getClientSocket() {
        if (clientSocket == null) {
            throw new UnsupportedOperationException("Servers need to be started");
        }
        return clientSocket;
    }

//    public boolean containsUser(@NotNull User user) {
//        return usersConnected.contains(user);
//    }
//
//    public void addUser(@NotNull User user) {
//        if (usersConnected.contains(user)) {
//            return;
//        }
//        usersConnected.add(user);
//    }
}
