package codes.ghostface.impl.utils;

import codes.ghostface.ClientPacket;
import codes.ghostface.ServerPacket;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

public final class PacketUtilsImpl implements PacketUtils {

    // Static initializers

    private static final @NotNull PacketUtilsImpl instance;

    static {
        instance = new PacketUtilsImpl();
    }

    public static @NotNull PacketUtils getInstance() {
        return instance;
    }

    // Implementations

    @Override
    public @NotNull <T extends ServerPacket> ServerPacketHandler getServerHandler(@NotNull T packet) {
        return new ServerPacketHandlerImpl(packet);
    }

    @Override
    public @NotNull <T extends ClientPacket> ClientPacketHandler getClientHandler(@NotNull T packet) {
        return new ClientPacketHandlerImpl(packet);
    }

    @Override
    public @NotNull <T extends ClientPacket> JsonElement deserialize(@NotNull T packet) {
        return null;
    }

    @Override
    public @NotNull <T extends ServerPacket> JsonElement deserialize(@NotNull T packet) {
        return null;
    }

    // Handlers

    public final class ClientPacketHandlerImpl implements ClientPacketHandler {

        private final @NotNull ClientPacket packet;
        private final @NotNull ClientCheckers checkers;

        public ClientPacketHandlerImpl(@NotNull ClientPacket packet) {
            this.packet = packet;
            this.checkers = new ClientCheckersImpl(packet);
        }

        @Override
        public @NotNull ClientPacket getPacket() {
            return packet;
        }

        @Override
        public @NotNull ClientCheckers getCheckers() {
            return checkers;
        }
    }

    public final class ServerPacketHandlerImpl implements ServerPacketHandler {

        private final @NotNull ServerPacket packet;
        private final @NotNull ServerCheckers checkers;

        public ServerPacketHandlerImpl(@NotNull ServerPacket packet) {
            this.packet = packet;
            this.checkers = new ServerCheckersImpl(packet);
        }

        @Override
        public @NotNull ServerPacket getPacket() {
            return packet;
        }

        @Override
        public @NotNull ServerCheckers getCheckers() {
            return checkers;
        }
    }

    // Checkers

    // TODO finish methods

    public final class ClientCheckersImpl implements ClientCheckers {

        private final @NotNull ClientPacket clientPacket;

        public ClientCheckersImpl(@NotNull ClientPacket clientPacket) {
            this.clientPacket = clientPacket;
        }

        @Override
        public boolean isConnection() {
            return false;
        }

        @Override
        public boolean isMessage() {
            return false;
        }

        @Override
        public boolean isData() {
            return false;
        }

        @Override
        public boolean isAuthentication() {
            return false;
        }

        @Override
        public boolean isRegister() {
            return false;
        }

        @Override
        public boolean isState() {
            return false;
        }

        @Override
        public boolean isCommand() {
            return false;
        }
    }

    public final class ServerCheckersImpl implements ServerCheckers {

        private final @NotNull ServerPacket serverPacket;

        public ServerCheckersImpl(@NotNull ServerPacket serverPacket) {
            this.serverPacket = serverPacket;
        }

        @Override
        public boolean isData() {
            return false;
        }

        @Override
        public boolean isMessage() {
            return false;
        }

        @Override
        public boolean isResponse() {
            return false;
        }

        @Override
        public boolean isError() {
            return false;
        }

        @Override
        public boolean isState() {
            return false;
        }
    }

    private PacketUtilsImpl() {
    }
}
