package codes.ghostface.impl.request;

import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ClientPacket;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public final class ClientMessagePacket extends ClientPacket {

    private final @NotNull String text;
    private final @NotNull OffsetDateTime time;

    public ClientMessagePacket(@NotNull String text, @NotNull OffsetDateTime time) {
        super(new JsonPacketImpl());
        this.text = text;
        this.time = time;
        model = getUtils().parseJson(this);
    }

    public @NotNull String getText() {
        return text;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }
}
