package codes.ghostface.jpacket.connection;

import codes.ghostface.jpacket.data.DataPacket;
import codes.ghostface.jpacket.element.client.ClientPacket;
import codes.ghostface.jpacket.element.server.ServerPacket;
import codes.ghostface.jpacket.exception.IllegalWriteModeException;
import codes.ghostface.jpacket.exception.PacketParseException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

public interface PacketConnection extends Closeable {

    @NotNull ClientPacket<?> read() throws IOException, PacketParseException;

    @Nullable InputStream read(@NotNull Duration timeout) throws IOException;

    void write(@NotNull ServerPacket<?> packet) throws IOException;

    void writeData(@NotNull ServerPacket<?> packet) throws IOException, IllegalWriteModeException;

    @Override
    void close() throws IOException;
}
