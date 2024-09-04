package codes.ghostface.impl.request;

import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.entity.Email;
import codes.ghostface.entity.Password;
import codes.ghostface.providers.ClientPacket;
import org.jetbrains.annotations.NotNull;

public final class ClientAuthPacket extends ClientPacket {

    private final @NotNull Email email;
    private final @NotNull Password password;

    public ClientAuthPacket(@NotNull Email email, @NotNull Password password) {
        super(new JsonPacketImpl());
        this.email = email;
        this.password = password;
        model = getUtils().parseJson(this);
    }

    public @NotNull Email getEmail() {
        return email;
    }

    public @NotNull Password getPassword() {
        return password;
    }
}
