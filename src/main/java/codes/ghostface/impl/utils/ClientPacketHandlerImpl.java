package codes.ghostface.impl.utils;

import codes.ghostface.ClientPacket;
import codes.ghostface.impl.client.AuthenticationPacket;
import codes.ghostface.impl.client.MessagePacket;
import codes.ghostface.impl.client.RegisterPacket;
import codes.ghostface.utils.PacketUtils.ClientCheckers;
import codes.ghostface.utils.PacketUtils.ClientPacketHandler;
import org.jetbrains.annotations.NotNull;

public final class ClientPacketHandlerImpl implements ClientPacketHandler {

    private final @NotNull ClientPacket packet;
    private final @NotNull ClientCheckers checkers;

    public ClientPacketHandlerImpl(@NotNull ClientPacket packet) {
        this.packet = packet;
        this.checkers = new ClientCheckersImpl();
    }

    @Override
    public @NotNull ClientPacket getPacket() {
        return packet;
    }

    @Override
    public @NotNull ClientCheckers getCheckers() {
        return checkers;
    }

    // Classes

    final class ClientCheckersImpl implements ClientCheckers {

        @Override
        public boolean isAuthentication() {
            return packet instanceof AuthenticationPacket;
        }

        @Override
        public boolean isRegister() {
            return packet instanceof RegisterPacket;
        }

        @Override
        public boolean isMessage() {
            return packet instanceof MessagePacket;
        }

        @Override
        public boolean isConnection() {
            return false;
        }

        @Override
        public boolean isData() {
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
}
