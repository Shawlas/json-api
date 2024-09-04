package codes.ghostface.response;

import codes.ghostface.model.Username;
import codes.ghostface.providers.JsonPacketModelImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ServerMessagePacket extends ServerPacket {

    private final @NotNull Username username;
    private final @NotNull String text;
    private final @NotNull OffsetDateTime time;

    public ServerMessagePacket(@NotNull Username username, @NotNull String text, @NotNull OffsetDateTime time) {
        super(new JsonPacketModelImpl());
        this.username = username;
        this.text = text;
        this.time = time;
        model = model.serialize(this);
    }

    public @NotNull String getText() {
        return text;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    public @NotNull Username getUsername() {
        return username;
    }

    @Override
    public String toString() {
        @NotNull String time = this.time.format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm"));
        return "[" + time + "] " + this.username + ": " + this.text;
    }
}
