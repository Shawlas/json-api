package codes.ghostface.request;

import codes.ghostface.model.Email;
import codes.ghostface.model.Password;
import codes.ghostface.model.Username;
import codes.ghostface.providers.JsonPacketModelImpl;
import codes.ghostface.providers.ClientPacket;
import org.jetbrains.annotations.NotNull;

public final class ClientRegisterPacket extends ClientPacket {

    private final @NotNull Email email;
    private final @NotNull Password password;
    private final @NotNull Username username;

    public ClientRegisterPacket(@NotNull Email email, @NotNull Password password, @NotNull Username username) {
        super(new JsonPacketModelImpl());
        this.email = email;
        this.password = password;
        this.username = username;
        model = model.serialize(this);
    }

    public @NotNull Email getEmail() {
        return email;
    }

    public @NotNull Password getPassword() {
        return password;
    }

    public @NotNull Username getUsername() {
        return username;
    }
}
