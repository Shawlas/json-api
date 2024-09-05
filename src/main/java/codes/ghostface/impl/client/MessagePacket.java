package codes.ghostface.impl.client;

import codes.ghostface.providers.AbstractClientPacket;
import codes.ghostface.type.ClientPacketType;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public class MessagePacket extends AbstractClientPacket {

    private final @NotNull String text;
    private final @NotNull OffsetDateTime time;

    public MessagePacket(@NotNull String text) {
        super(ClientPacketType.MESSAGE, new JsonObject(), PacketUtils.getInstance());
        this.text = text;
        this.time = OffsetDateTime.now();
        values.addProperty("type", getType().getDescription());
        values.addProperty("text", text);
        values.addProperty("time", time.toString());
    }

    // Getters

    public @NotNull String getText() {
        return text;
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
