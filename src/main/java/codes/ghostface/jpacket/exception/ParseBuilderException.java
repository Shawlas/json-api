package codes.ghostface.jpacket.exception;

import org.jetbrains.annotations.NotNull;

public class ParseBuilderException extends PacketParseException {
    public ParseBuilderException(String message) {
        super(message);
    }

    public ParseBuilderException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
