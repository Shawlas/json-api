package codes.ghostface.jpacket.builder;

import codes.ghostface.jpacket.exception.ParseBuilderException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.NotNull;


import java.io.*;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public final class JsonPacketBuilder extends PacketBuilder<@NotNull JsonElement> {

    private final @NotNull StringBuilder builder = new StringBuilder();
    private final @NotNull CharBuffer buffer;
    private final int jsonSize;
    private final int inSize;

    public JsonPacketBuilder(@NotNull InputStream in, int inSize, int jsonSize) {
        super(in);
        this.jsonSize = jsonSize;
        this.inSize = inSize;
        this.buffer = CharBuffer.allocate(jsonSize);
    }

    public JsonPacketBuilder(@NotNull String packet, int inSize ,int jsonSize) {
        super(packet);
        this.jsonSize = jsonSize;
        this.inSize = inSize;
        this.buffer = CharBuffer.allocate(jsonSize);
    }

    @Override
    public @NotNull JsonElement readPacket() throws ParseBuilderException, IOException {
        long ms = System.currentTimeMillis();
        if (inSize > jsonSize && marksuported) {
            return readIfMarkSuported();
        } else try (@NotNull InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            buffer.clear();

            int read = reader.read(buffer);
            while (read > 0) {
                buffer.flip();
                builder.append(buffer, 0 , read);
                buffer.clear();

                read = reader.read(buffer);
            }

            @NotNull JsonElement json = JsonParser.parseString(builder.toString());
            System.out.println("Time : " + (System.currentTimeMillis() - ms));
            return json;

        } catch (@NotNull JsonSyntaxException e) {
            @NotNull String[] parts = builder.toString().split("\r\n\r\n");
            if (parts.length != 2) {
                throw new ParseBuilderException("Invalid packet format");
            }

            try {
                @NotNull JsonElement json = JsonParser.parseString(parts[1]);
                System.out.println("Time : " + (System.currentTimeMillis() - ms));
                return json;
            } catch (@NotNull JsonSyntaxException ex) {
                throw new ParseBuilderException("Invalid json syntax");
            }
        }
    }

    private @NotNull JsonElement readIfMarkSuported() throws ParseBuilderException, IOException {
        long ms = System.currentTimeMillis();
        if (!marksuported) {
            throw new ParseBuilderException("InputStream is not a mark Suported");
        } else try (@NotNull InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {

            int n = inSize - jsonSize;
            long skipped = stream.skip(n);

            if (skipped < n) {
                // todo: Do something
            }

            stream.mark(jsonSize);

            int read = reader.read(buffer);
            while (read > 0) {
                buffer.flip();
                builder.append(buffer, 0, read);
                buffer.clear();

                read = reader.read(buffer);
            }

            @NotNull JsonElement json = JsonParser.parseString(builder.toString());
            System.out.println("Time : " + (System.currentTimeMillis() - ms));
            return json;
        } catch (@NotNull JsonSyntaxException e) {
            throw new ParseBuilderException("Invalid json syntax");
        }
    }
}
