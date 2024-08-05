package com.ghostface.dev.utilities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Reader {

    public static @Nullable String of(@NotNull Socket client, @NotNull String str) {
        try {
            @NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            return str = reader.readLine();
        } catch (IOException e) {
            System.err.println("Failed to read field");
            e.printStackTrace();
        }
        return null;
    }

    private Reader() {
        throw new UnsupportedOperationException();
    }
}
