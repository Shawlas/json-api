package codes.ghostface.impl.client;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.models.Email;
import codes.ghostface.models.Username;
import codes.ghostface.providers.AbstractClientPacket;
import codes.ghostface.type.ClientType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public final class RegisterPacket extends AbstractClientPacket {

    private final @NotNull Email email;
    private final @NotNull Username username;
    private final @NotNull String password;
    private final @NotNull OffsetDateTime time;

    public RegisterPacket(@NotNull Email email, @NotNull Username username, @NotNull String password) {
        super(ClientType.REGISTER, new JsonObject(), new PacketUtilsImpl());
        this.email = email;
        this.username = username;
        this.password = password;
        this.time = OffsetDateTime.now();
        values.addProperty("email", getEmail().toString());
        values.addProperty("username", getUsername().toString());
        values.addProperty("password", getPassword());
        values.addProperty("time", getTime().toString());
    }

    // Getters

    public @NotNull Email getEmail() {
        return email;
    }

    public @NotNull Username getUsername() {
        return username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    @Override
    public @NotNull JsonElement getValues() {
        @NotNull JsonObject element = new JsonObject();
        element.add("value", values);
        return element;
    }
}
