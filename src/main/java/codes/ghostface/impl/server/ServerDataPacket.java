package codes.ghostface.impl.server;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.providers.AbstractServerPacket;
import codes.ghostface.type.ServerType;
import codes.laivy.serializable.json.JsonSerializable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.Objects;

public final class ServerDataPacket<T extends Serializable> extends AbstractServerPacket {

    private final @NotNull T object;
    private final @NotNull JsonElement data;

    public ServerDataPacket(@NotNull T object) throws InvalidClassException {
        super(ServerType.DATA, new JsonObject(), new PacketUtilsImpl());
        this.object = object;
        this.data = Objects.requireNonNull(new JsonSerializable().serialize(getObject()));
        values.add("datas", getData());
    }

    public @NotNull T getObject() {
        return object;
    }

    public @NotNull JsonElement getData() {
        return data;
    }

    @Override
    public @NotNull JsonElement getValues() {
        @NotNull JsonObject object = new JsonObject();
        object.add("values", values);
        return object;
    }
}
