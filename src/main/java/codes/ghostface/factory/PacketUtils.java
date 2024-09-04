package codes.ghostface.factory;

import codes.ghostface.factory.model.JsonPacketModel;
import org.jetbrains.annotations.NotNull;

public interface PacketUtils {

    @NotNull JsonPacketModel parseJson(@NotNull Packet packet);


}
