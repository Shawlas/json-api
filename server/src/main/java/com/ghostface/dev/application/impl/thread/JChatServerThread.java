package com.ghostface.dev.application.impl.thread;

import com.ghostface.dev.application.impl.UserImpl;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JChatServerThread extends Thread {

    private final @NotNull Socket client;
    private final @NotNull Set<@NotNull UserImpl> userImpls = ConcurrentHashMap.newKeySet();

    public JChatServerThread(@NotNull Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Trying connection: " + client.getInetAddress().getHostAddress());
        // catch username
        @NotNull Thread screening = new ScreeningThread(this);
        // listenen the message
        screening.start();
    }

    public @NotNull Socket getSocket() {
        return client;
    }

    public boolean addUser(@NotNull UserImpl userImpl) {
        return userImpls.add(userImpl);
    }


}
