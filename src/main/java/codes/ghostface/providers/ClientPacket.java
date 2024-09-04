package codes.ghostface.providers;

import codes.ghostface.factory.Packet;

import org.jetbrains.annotations.NotNull;

public abstract class ClientPacket implements Packet {

    protected @NotNull JsonPacketModelImpl model;

    protected ClientPacket(@NotNull JsonPacketModelImpl model) {
        this.model = model;
    }

    @Override
    public final @NotNull JsonPacketModelImpl getValues() {
        return model;
    }

}
