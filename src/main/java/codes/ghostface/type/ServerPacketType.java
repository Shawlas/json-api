package codes.ghostface.type;

import org.jetbrains.annotations.NotNull;

public enum ServerPacketType {
    DATA("data"),
    MESSAGE("message"),
    RESPONSE("response"),
    ERROR("error"),
    STATE("state");

    private final String description;

    ServerPacketType(@NotNull String description) {
        this.description = description;
    }

    @NotNull
    public String getDescription() {
        return description;
    }
}
