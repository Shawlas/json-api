package com.ghostface.dev.threads;

import com.ghostface.dev.application.User;
import com.ghostface.dev.utilities.Reader;
import com.ghostface.dev.utilities.Writer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Socket;
import java.util.Set;

public class Insertion implements Runnable {

    private final @NotNull Set<@NotNull User> usersConnected;
    private final @NotNull Socket socket;
    private @Nullable Message msg;

    public Insertion(@NotNull Set<@NotNull User> usersConnected, @NotNull Socket socket) {
        this.usersConnected = usersConnected;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            for (@NotNull User user : usersConnected) {
                @Nullable String message = Reader.of(socket);
                msg = new Message(user, message);
                Writer.to(socket, msg.getDate() + " - " + msg.getUser().getUsername() + ": " + msg.getMsg());
            }
        }
    }
}
