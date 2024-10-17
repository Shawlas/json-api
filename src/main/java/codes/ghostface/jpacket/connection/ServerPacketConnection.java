package codes.ghostface.jpacket.connection;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;

public abstract class ServerPacketConnection {

    // Objects

    protected volatile @Nullable ServerSocket server;
    protected @Nullable Selector selector;
    private final @NotNull InetSocketAddress address;

    protected ServerPacketConnection(@NotNull InetSocketAddress address) {
        this.address = address;
    }

    public @NotNull InetSocketAddress getAdress() {
        return address;
    }

    public @NotNull Optional<ClientPacketConnection> getClient(@NotNull Socket socket) {
        return getClients().stream().filter(c -> c.getSocket().equals(socket)).findFirst();
    }

    public @NotNull Optional<ClientPacketConnection> getClient(@NotNull SelectionKey key) {
        @NotNull SocketChannel socket = (SocketChannel) key.channel();
        return getClients().stream().filter(c -> c.getSocket().equals(socket.socket())).findFirst();
    }

    public abstract @NotNull Collection<ClientPacketConnection> getClients();

    public abstract boolean start() throws IOException;

    public abstract boolean stop() throws IOException;

}
