package codes.ghostface.impl.utils;

import codes.ghostface.ServerPacket;
import codes.ghostface.impl.server.ServerErrorPacket;
import codes.ghostface.impl.server.ServerResponsePacket;
import codes.ghostface.utils.PacketUtils.ServerCheckers;
import codes.ghostface.utils.PacketUtils.ServerPacketHandler;
import org.jetbrains.annotations.NotNull;

public final class ServerPacketHandlerImpl implements ServerPacketHandler {

    private final @NotNull ServerPacket packet;
    private final @NotNull ServerCheckers checkers;

    public ServerPacketHandlerImpl(@NotNull ServerPacket packet) {
        this.packet = packet;
        this.checkers = new ServerCheckersImpl();
    }

    @Override
    public @NotNull ServerPacket getPacket() {
        return packet;
    }

    @Override
    public @NotNull ServerCheckers getCheckers() {
        return checkers;
    }

    // Classes

    final class ServerCheckersImpl implements ServerCheckers {

        @Override
        public boolean isResponse() {
            return packet instanceof ServerResponsePacket;
        }

        @Override
        public boolean isError() {
            return packet instanceof ServerErrorPacket;
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
        public boolean isState() {
            return false;
        }
    }
}
