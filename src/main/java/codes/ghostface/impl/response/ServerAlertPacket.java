package codes.ghostface.impl.response;

import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public class ServerAlertPacket extends ServerPacket {

    private final @NotNull Alert alert;

    public ServerAlertPacket(@NotNull Alert alert) {
        super(new JsonPacketImpl());
        this.alert = alert;
        model = getUtils().parseJson(this);
    }

    public @NotNull Alert getAlert() {
        return alert;
    }

    public enum Alert {
        SPAM,
    }
}
