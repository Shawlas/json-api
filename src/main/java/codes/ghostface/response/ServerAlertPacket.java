package codes.ghostface.response;

import codes.ghostface.providers.JsonPacketModelImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public class ServerAlertPacket extends ServerPacket {

    private final @NotNull Alert alert;

    public ServerAlertPacket(@NotNull Alert alert) {
        super(new JsonPacketModelImpl());
        this.alert = alert;
        model = model.serialize(this);
    }

    public @NotNull Alert getAlert() {
        return alert;
    }

    public enum Alert {
        SPAM,
    }
}
