package codes.ghostface.protocol;

import codes.ghostface.factory.protocol.RequestPacket;
import codes.ghostface.impl.response.ServerAlertPacket;
import codes.ghostface.impl.response.ServerErrorPacket;
import codes.ghostface.impl.response.ServerMessagePacket;
import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ServerPacket;
import codes.ghostface.utils.PacketUtilsImpl;
import org.jetbrains.annotations.NotNull;

public final class RequestPacketImpl implements RequestPacket {

    private final @NotNull ServerPacket serverPacket;

    public RequestPacketImpl(@NotNull ServerPacket serverPacket) {
        this.serverPacket = serverPacket;
    }

    // Implementation

    @Override
    public @NotNull JsonPacketImpl getValues() {
        return serverPacket.getValues();
    }

    @Override
    public @NotNull PacketUtilsImpl getUtils() {
        return serverPacket.getUtils();
    }

    @Override
    public @NotNull ServerPacket getPacket() {
        return serverPacket;
    }

    @Override
    public boolean isError() {
        return serverPacket instanceof ServerErrorPacket;
    }

    @Override
    public boolean isServerMessage() {
        return serverPacket instanceof ServerMessagePacket;
    }

    @Override
    public boolean isAlert() {
        return serverPacket instanceof ServerAlertPacket;
    }
}
