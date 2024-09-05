package codes.ghostface.providers;

import codes.ghostface.ClientPacket;
import codes.ghostface.type.ClientPacketType;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractClientPacket implements ClientPacket {

    protected final @NotNull JsonObject values;
    private final @NotNull ClientPacketType type;
    private final @NotNull PacketUtils utils;

    protected AbstractClientPacket(@NotNull ClientPacketType type, @NotNull JsonObject values, @NotNull PacketUtils utils) {
        this.type = type;
        this.values = values;
        this.utils = utils;
    }

    @Override
    public final @NotNull ClientPacketType getType() {
        return type;
    }

    @Override
    public final @NotNull PacketUtils getUtils() {
        return utils;
    }

    @Override
    public @NotNull String toString() {
        return values.toString();
    }
}
