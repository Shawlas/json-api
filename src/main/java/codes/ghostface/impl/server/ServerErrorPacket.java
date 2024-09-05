package codes.ghostface.impl.server;

import codes.ghostface.providers.AbstractServerPacket;

import codes.ghostface.type.ServerPacketType;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public class ServerErrorPacket extends AbstractServerPacket {

    private final @NotNull String cause;
    private final @NotNull OffsetDateTime time;

    public ServerErrorPacket(@NotNull String cause, @NotNull OffsetDateTime dateTime) {
        super(ServerPacketType.ERROR, new JsonObject(), PacketUtils.getInstance());
        this.cause = cause;
        this.time = dateTime;
        values.addProperty("type", getType().getDescription());
        values.addProperty("cause", cause);
        values.addProperty("time", time.toString());
    }

    // Getters

    public @NotNull String getCause() {
        return cause;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    @Override
    public @NotNull JsonElement getValues() {
        @NotNull JsonObject element = new JsonObject();
        element.add("value", values);
        return element;
    }
}
