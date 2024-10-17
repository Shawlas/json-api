package codes.ghostface.jpacket.connection;

import codes.ghostface.jpacket.element.client.ClientPacket;
import codes.ghostface.jpacket.element.server.ServerPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

public interface ClientPacketConnection extends Closeable {

    @NotNull Socket getSocket();

    @NotNull InetSocketAddress getAddress();

    @NotNull Duration time();

    @NotNull Instant lastConnection();

    @NotNull ClientPacket<?> read() throws IOException;

    @Nullable InputStream read(@NotNull Duration timeout);

    default void write(@NotNull ServerPacket<?> packet) throws IOException {
        packet.getBuilder().write(getSocket().getOutputStream());
    }

    boolean isOpen();

    @Override
    void close() throws IOException;
}
