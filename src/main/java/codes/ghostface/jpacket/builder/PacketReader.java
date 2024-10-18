package codes.ghostface.jpacket.builder;


import codes.ghostface.jpacket.exception.ParseBuilderException;
import org.jetbrains.annotations.NotNull;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class PacketReader<T> {

    protected final @NotNull InputStream stream;
    protected final boolean marksuported;

    protected PacketReader(@NotNull InputStream in) {
        this.stream = in;
        this.marksuported = stream.markSupported();
    }

    protected PacketReader(@NotNull String packet) {
        this.stream = new ByteArrayInputStream(packet.getBytes());
        this.marksuported = stream.markSupported();
    }

    public abstract @NotNull T readPacket() throws IOException, ParseBuilderException;

}
