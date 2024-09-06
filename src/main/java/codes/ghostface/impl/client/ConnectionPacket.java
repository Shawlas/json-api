package codes.ghostface.impl.client;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.providers.AbstractClientPacket;
import codes.ghostface.type.ClientType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public final class ConnectionPacket extends AbstractClientPacket {

    private final @NotNull Scope scope;

    public ConnectionPacket(@NotNull Scope scope) {
        super(ClientType.CONNECTION, new JsonObject(), new PacketUtilsImpl());
        this.scope = scope;
        values.addProperty("scope", getScope().name().toLowerCase());
    }

    public @NotNull Scope getScope() {
        return scope;
    }

    @Override
    public @NotNull JsonElement getValues() {
        @NotNull JsonObject object = new JsonObject();
        object.add("values", values);
        return object;
    }

    public enum Scope {
        DISCONNECT,
    }
}
