package codes.ghostface.factory;

import codes.ghostface.factory.model.JsonPacketModel;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public interface Packet extends Serializable {

    @NotNull JsonPacketModel getValues();

    @NotNull PacketUtils getUtils();

}
