package codes.ghostface.impl.client;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.providers.AbstractClientPacket;
import codes.ghostface.type.ClientType;

import codes.laivy.serializable.json.JsonSerializable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.Objects;

public final class DataPacket<T extends Serializable> extends AbstractClientPacket {

    private final @NotNull T object;
    private final @NotNull JsonElement data;

    public DataPacket(@NotNull T object) throws InvalidClassException {
        super(ClientType.DATA, new JsonObject(), new PacketUtilsImpl());
        this.object = object;
        this.data = Objects.requireNonNull(new JsonSerializable().serialize(getObject()));
        values.add("datas", getData());
    }

    @NotNull
    public T getObject() {
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
