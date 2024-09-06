package codes.ghostface.impl.server;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.models.Message;
import codes.ghostface.providers.AbstractServerPacket;
import codes.ghostface.type.ServerType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public final class ServerMessagePacket extends AbstractServerPacket {

    private final @NotNull Message message;

    public ServerMessagePacket(@NotNull Message message) {
        super(ServerType.MESSAGE, new JsonObject(), new PacketUtilsImpl());
        this.message = message;
        values.addProperty("username", getMessage().getUsername().toString());
        values.addProperty("text", getMessage().getContent());
        values.addProperty("time", getMessage().getTime().toString());
    }

    // Getters

    public @NotNull Message getMessage() {
        return message;
    }

    @Override
    public @NotNull JsonElement getValues() {
        @NotNull JsonObject object = new JsonObject();
        object.add("values", values);
        return object;
    }
}
