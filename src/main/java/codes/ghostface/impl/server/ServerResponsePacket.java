package codes.ghostface.impl.server;

import codes.ghostface.providers.AbstractServerPacket;
import codes.ghostface.type.ServerPacketType;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public class ServerResponsePacket extends AbstractServerPacket {

    private final @NotNull String content;
    private final @NotNull OffsetDateTime time;

    public ServerResponsePacket(@NotNull String content, @NotNull OffsetDateTime dateTime) {
        super(ServerPacketType.RESPONSE, new JsonObject(), PacketUtils.getInstance());
        this.content = content;
        this.time = dateTime;
        values.addProperty("type", getType().getDescription());
        values.addProperty("content", content);
        values.addProperty("time", time.toString());
    }

    // Getters

    public @NotNull String getContent() {
        return content;
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
