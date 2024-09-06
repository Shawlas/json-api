package codes.ghostface.impl.server;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.providers.AbstractServerPacket;
import codes.ghostface.type.ServerType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public final class ServerResponsePacket extends AbstractServerPacket {

    private final @NotNull String content;
    private final @NotNull OffsetDateTime time;

    public ServerResponsePacket(@NotNull String content, @NotNull OffsetDateTime dateTime) {
        super(ServerType.RESPONSE, new JsonObject(), new PacketUtilsImpl());
        this.content = content;
        this.time = dateTime;
        values.addProperty("content", getContent());
        values.addProperty("time", getTime().toString());
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
