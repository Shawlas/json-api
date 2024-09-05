package codes.ghostface;

import codes.ghostface.type.ClientPacketType;
import org.jetbrains.annotations.NotNull;

public interface ClientPacket extends Packet {

    @NotNull ClientPacketType getType();

}
