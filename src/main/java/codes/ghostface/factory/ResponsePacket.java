package codes.ghostface.factory;

import codes.ghostface.providers.ClientPacket;
import org.jetbrains.annotations.NotNull;

public interface ResponsePacket extends Packet {

    @NotNull ClientPacket getPacket();

    boolean isAuthentication();

    boolean isRegister();

    boolean isMessage();

}
