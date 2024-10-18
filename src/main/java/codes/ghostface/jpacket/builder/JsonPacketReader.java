package codes.ghostface.jpacket.builder;

import codes.ghostface.jpacket.exception.ParseBuilderException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.NotNull;

import java.io.*;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public final class JsonPacketReader extends PacketReader<@NotNull JsonElement> {

    private final @NotNull StringBuilder builder = new StringBuilder();
    private final @NotNull CharBuffer buffer;
    private final int jsonSize;
    private final int inSize;

    public JsonPacketReader(@NotNull InputStream in, int inSize, int jsonSize) {
        super(in);
        this.jsonSize = jsonSize;
        this.inSize = inSize;
        this.buffer = CharBuffer.allocate(jsonSize);
    }

    public JsonPacketReader(@NotNull String packet, int inSize, int jsonSize) {
        super(packet);
        this.jsonSize = jsonSize;
        this.inSize = inSize;
        this.buffer = CharBuffer.allocate(jsonSize);
    }

    @Override
    public @NotNull JsonElement readPacket() throws ParseBuilderException, IOException {
        if (inSize > jsonSize && marksuported) {
            return readIfMarkSuported();
        } else
            return readIfHasNoMark();
    }

    private @NotNull JsonElement readIfMarkSuported() throws IOException, ParseBuilderException {
        if (!marksuported) {
            throw new ParseBuilderException("InputStream is not a mark Suported");
        } else try (@NotNull InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            stream.mark(inSize);
            int n = inSize - jsonSize;
            long skipped = stream.skip(n);

            // if #Skip doesn't work
            if (skipped < n) {
                stream.reset();
                reader.close();
                return readIfHasNoMark();
            } else {
                stream.mark(jsonSize);

                int read = reader.read(buffer);
                while (read > 0) {
                    buffer.flip();
                    builder.append(buffer, 0, read);
                    buffer.clear();

                    read = reader.read(buffer);
                }

                @NotNull JsonElement json = JsonParser.parseString(builder.toString());
                return json;
            }
        } catch (@NotNull JsonSyntaxException e) {
            throw new ParseBuilderException("Invalid json syntax or packet format");
        }
    }

    private @NotNull JsonElement readIfHasNoMark() throws IOException, ParseBuilderException {
        try (@NotNull InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            buffer.clear();

            int read = reader.read(buffer);
            while (read > 0) {
                buffer.flip();
                builder.append(buffer, 0 , read);
                buffer.clear();

                read = reader.read(buffer);
            }

            @NotNull JsonElement json = JsonParser.parseString(builder.toString());
            return json;

        } catch (@NotNull JsonSyntaxException e) {
            @NotNull String[] parts = builder.toString().split("\r\n\r\n");
            if (parts.length != 2) {
                throw new ParseBuilderException("Invalid packet format");
            } else try {
                @NotNull JsonElement json = JsonParser.parseString(parts[1]);
                return json;
            } catch (@NotNull JsonSyntaxException ex) {
                throw new ParseBuilderException("Invalid json syntax or packet format");
            }
        }
    }
}
