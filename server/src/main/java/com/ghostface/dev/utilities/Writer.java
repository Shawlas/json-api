package com.ghostface.dev.utilities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Writer {

    public static void to(@NotNull Socket client, @NotNull String str) {
        try {
            @NotNull PrintWriter writer = new PrintWriter(client.getOutputStream());
            writer.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Writer() {
        throw new UnsupportedOperationException();
    }
}
