package codes.ghostface;

import codes.ghostface.factory.ResponsePacket;
import codes.ghostface.request.ClientAuthPacket;
import codes.ghostface.request.ClientMessagePacket;
import codes.ghostface.request.ClientRegisterPacket;
import codes.ghostface.providers.ClientPacket;
import codes.ghostface.providers.JsonPacketModelImpl;

import org.jetbrains.annotations.NotNull;

public final class ResponsePacketImpl implements ResponsePacket {

    private final @NotNull ClientPacket clientPacket;

    public ResponsePacketImpl(@NotNull ClientPacket clientPacket) {
        this.clientPacket = clientPacket;
    }

    // Implementation

    @Override
    public @NotNull JsonPacketModelImpl getValues() {
        return clientPacket.getValues();
    }

    @Override
    public @NotNull ClientPacket getPacket() {
        return clientPacket;
    }

    @Override
    public boolean isAuthentication() {
        return clientPacket instanceof ClientAuthPacket;
    }

    @Override
    public boolean isRegister() {
        return clientPacket instanceof ClientRegisterPacket;
    }

    @Override
    public boolean isMessage() {
        return clientPacket instanceof ClientMessagePacket;
    }
}
