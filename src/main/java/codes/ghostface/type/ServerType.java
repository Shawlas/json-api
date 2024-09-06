package codes.ghostface.type;

import org.jetbrains.annotations.NotNull;

public enum ServerType {
    DATA("data"),
    MESSAGE("message"),
    RESPONSE("response"),
    ERROR("error"),
    STATE("state");

    private final String description;

    ServerType(@NotNull String description) {
        this.description = description;
    }

    @NotNull
    public String getDescription() {
        return description;
    }
}
