package codes.ghostface.impl.server;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.providers.AbstractServerPacket;

import codes.ghostface.type.ServerType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public final class ServerErrorPacket extends AbstractServerPacket {

    private final @NotNull Throwable cause;
    private final @NotNull OffsetDateTime time;

    public ServerErrorPacket(@NotNull Throwable cause, @NotNull OffsetDateTime dateTime) {
        super(ServerType.ERROR, new JsonObject(), new PacketUtilsImpl());
        this.cause = cause;
        this.time = dateTime;
        values.addProperty("cause", getCause().getMessage());
        values.addProperty("time", getTime().toString());
    }

    // Getters

    public @NotNull Throwable getCause() {
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
