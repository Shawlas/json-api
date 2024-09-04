package codes.ghostface.impl.request;

import codes.ghostface.entity.Email;
import codes.ghostface.entity.Password;
import codes.ghostface.entity.Username;
import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ClientPacket;
import org.jetbrains.annotations.NotNull;

public final class ClientRegisterPacket extends ClientPacket {

    private final @NotNull Email email;
    private final @NotNull Password password;
    private final @NotNull Username username;

    public ClientRegisterPacket(@NotNull Email email, @NotNull Password password, @NotNull Username username) {
        super(new JsonPacketImpl());
        this.email = email;
        this.password = password;
        this.username = username;
        model = getUtils().parseJson(this);
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
