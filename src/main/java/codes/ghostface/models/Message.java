package codes.ghostface.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Message implements Serializable {

    private final @NotNull Username username;
    private final @NotNull String text;
    private final @NotNull Instant time;

    public Message(@NotNull Username username, @NotNull String text, @NotNull Instant time) {
        this.username = username;
        this.text = text;
        this.time = time;
    }

    public @NotNull Username getUsername() {
        return username;
    }

    public @NotNull String getContent() {
        return text;
    }

    public @NotNull Instant getTime() {
        return time;
    }

    // Natives

    @Override
    public String toString() {
        @NotNull String time = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")
                .withZone(ZoneId.systemDefault())
                .format(this.time);
        return "[" + time + "] " + this.username + ": " + this.text;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Message message = (Message) object;
        return Objects.equals(username, message.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
