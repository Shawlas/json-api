package codes.ghostface.response;

import codes.ghostface.providers.JsonPacketModelImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public class ServerErrorPacket extends ServerPacket {

    private final @NotNull Cause cause;

    public ServerErrorPacket(@NotNull Cause cause) {
        super(new JsonPacketModelImpl());
        this.cause = cause;
        model = model.serialize(this);
    }

    public @NotNull Cause getCause() {
        return cause;
    }

    public enum Cause {
        EXISTING_EMAIL,
        EXISTING_USERNAME,
        NOT_FOUND,
        UNKNOWN,
    }
}
