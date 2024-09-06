package codes.ghostface.providers;

import codes.ghostface.ServerPacket;
import codes.ghostface.type.ServerType;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractServerPacket implements ServerPacket {

    protected final @NotNull JsonObject values;
    private final @NotNull ServerType type;
    private final @NotNull PacketUtils utils;

    protected AbstractServerPacket(@NotNull ServerType type, @NotNull JsonObject values, @NotNull PacketUtils utils) {
        this.type = type;
        this.values = values;
        this.utils = utils;
        this.values.addProperty("type", getType().getDescription());
    }

    @Override
    public final @NotNull ServerType getType() {
        return type;
    }

    @Override
    public final @NotNull PacketUtils getUtils() {
        return utils;
    }
}
