package com.ghostface.dev;

import com.ghostface.dev.application.ChatService;
import org.jetbrains.annotations.NotNull;

public class ServerApplication {
    public static void main(String[] args) {
        @NotNull ChatService chat = new ChatService();
        chat.start();
    }
}
