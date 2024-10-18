package codes.ghostface.jpacket.exception;

import org.jetbrains.annotations.NotNull;

public class PacketParseException extends Exception {
    public PacketParseException(@NotNull String message) {
        super(message);
    }

    public PacketParseException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
