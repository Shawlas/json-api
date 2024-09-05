package codes.ghostface.type;

import org.jetbrains.annotations.NotNull;

public enum ClientPacketType {
    CONNECTION("connection"),
    MESSAGE("message"),
    AUTHENTICATION("authentication"),
    DATA("data"),
    COMMAND("command"),
    STATE("state");

    private final @NotNull String description;

    ClientPacketType(@NotNull String description) {
        this.description = description;
    }

    public @NotNull String getDescription() {
        return description;
    }
}

