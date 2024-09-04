package codes.ghostface.factory;

import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public interface RequestPacket extends Packet {

    @NotNull ServerPacket getPacket();

    boolean isError();

    boolean isServerMessage();

    boolean isAlert();

}
