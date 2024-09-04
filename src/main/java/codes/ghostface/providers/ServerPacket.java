package codes.ghostface.providers;

import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.factory.Packet;

import codes.ghostface.utils.PacketUtilsImpl;
import org.jetbrains.annotations.NotNull;

public abstract class ServerPacket implements Packet {

    private final @NotNull PacketUtilsImpl utils;
    protected @NotNull JsonPacketImpl model;

    protected ServerPacket(@NotNull JsonPacketImpl model) {
        this.model = model;
        this.utils = new PacketUtilsImpl();
    }

    @Override
    public final @NotNull JsonPacketImpl getValues() {
        return model;
    }

    @Override
    public @NotNull PacketUtilsImpl getUtils() {
        return utils;
    }
}
