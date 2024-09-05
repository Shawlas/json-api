package codes.ghostface.providers;

import codes.ghostface.ServerPacket;
import codes.ghostface.type.ServerPacketType;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractServerPacket implements ServerPacket {

    protected final @NotNull JsonObject values;
    private final @NotNull ServerPacketType type;
    private final @NotNull PacketUtils utils;

    protected AbstractServerPacket(@NotNull ServerPacketType type, @NotNull JsonObject values, @NotNull PacketUtils utils) {
        this.type = type;
        this.values = values;
        this.utils = utils;
    }

    @Override
    public final @NotNull ServerPacketType getType() {
        return type;
    }

    @Override
    public final @NotNull PacketUtils getUtils() {
        return utils;
    }
}
