package codes.ghostface;

import codes.ghostface.type.ClientType;
import org.jetbrains.annotations.NotNull;

public interface ClientPacket extends Packet {

    @NotNull
    ClientType getType();

}
