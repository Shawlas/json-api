package codes.ghostface;

import codes.ghostface.type.ServerPacketType;
import org.jetbrains.annotations.NotNull;

public interface ServerPacket extends Packet {

    @NotNull ServerPacketType getType();

}
