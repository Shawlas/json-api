package codes.ghostface.request;

import codes.ghostface.providers.JsonPacketModelImpl;
import codes.ghostface.model.Email;
import codes.ghostface.model.Password;
import codes.ghostface.providers.ClientPacket;
import org.jetbrains.annotations.NotNull;

public final class ClientAuthPacket extends ClientPacket {

    private final @NotNull Email email;
    private final @NotNull Password password;

    public ClientAuthPacket(@NotNull Email email, @NotNull Password password) {
        super(new JsonPacketModelImpl());
        this.email = email;
        this.password = password;
        model = getValues().serialize(this);
    }

    public @NotNull Email getEmail() {
        return email;
    }

    public @NotNull Password getPassword() {
        return password;
    }
}
