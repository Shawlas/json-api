package codes.ghostface.factory.protocol;

import codes.ghostface.factory.Packet;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

public interface RequestPacket extends Packet {

    @NotNull ServerPacket getPacket();

    boolean isError();

    boolean isServerMessage();

    boolean isAlert();

}
