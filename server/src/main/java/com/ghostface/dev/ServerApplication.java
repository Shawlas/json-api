package com.ghostface.dev;

import com.ghostface.dev.application.Jchat;
import com.ghostface.dev.application.screening.Username;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        @NotNull Jchat jchat = new Jchat(8080);
        System.out.println(Username.validate("shaolin_faze"));
    }
}
