package codes.ghostface.type;

import org.jetbrains.annotations.NotNull;

public enum ClientType {
    CONNECTION("connection"),
    MESSAGE("message"),
    AUTHENTICATION("authentication"),
    REGISTER("register"),
    DATA("data"),
    COMMAND("command"),
    STATE("state");

    private final @NotNull String description;

    ClientType(@NotNull String description) {
        this.description = description;
    }

    public @NotNull String getDescription() {
        return description;
    }
}

