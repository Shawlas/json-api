package codes.ghostface.protocol;

import codes.ghostface.factory.protocol.ResponsePacket;
import codes.ghostface.impl.request.ClientAuthPacket;
import codes.ghostface.impl.request.ClientMessagePacket;
import codes.ghostface.impl.request.ClientRegisterPacket;
import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ClientPacket;

import codes.ghostface.utils.PacketUtilsImpl;
import org.jetbrains.annotations.NotNull;

public final class ResponsePacketImpl implements ResponsePacket {

    private final @NotNull ClientPacket clientPacket;

    public ResponsePacketImpl(@NotNull ClientPacket clientPacket) {
        this.clientPacket = clientPacket;
    }

    // Implementation

    @Override
    public @NotNull JsonPacketImpl getValues() {
        return clientPacket.getValues();
    }

    @Override
    public @NotNull ClientPacket getPacket() {
        return clientPacket;
    }

    @Override
    public @NotNull PacketUtilsImpl getUtils() {
        return clientPacket.getUtils();
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
