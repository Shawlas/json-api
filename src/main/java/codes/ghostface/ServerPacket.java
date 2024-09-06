package codes.ghostface;

import codes.ghostface.type.ServerType;
import org.jetbrains.annotations.NotNull;

public interface ServerPacket extends Packet {

    @NotNull
    ServerType getType();

}
