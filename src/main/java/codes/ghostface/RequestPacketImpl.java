package codes.ghostface;

import codes.ghostface.factory.RequestPacket;
import codes.ghostface.response.ServerAlertPacket;
import codes.ghostface.response.ServerErrorPacket;
import codes.ghostface.response.ServerMessagePacket;
import codes.ghostface.providers.JsonPacketModelImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public final class RequestPacketImpl implements RequestPacket {

    private final @NotNull ServerPacket serverPacket;

    public RequestPacketImpl(@NotNull ServerPacket serverPacket) {
        this.serverPacket = serverPacket;
    }

    // Implementation

    @Override
    public @NotNull JsonPacketModelImpl getValues() {
        return serverPacket.getValues();
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
