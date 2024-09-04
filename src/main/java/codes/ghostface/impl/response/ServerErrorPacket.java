package codes.ghostface.impl.response;

import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public class ServerErrorPacket extends ServerPacket {

    private final @NotNull Cause cause;

    public ServerErrorPacket(@NotNull Cause cause) {
        super(new JsonPacketImpl());
        this.cause = cause;
        model = getUtils().parseJson(this);
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
