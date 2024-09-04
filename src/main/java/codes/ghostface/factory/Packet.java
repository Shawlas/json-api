package codes.ghostface.factory;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public interface Packet extends Serializable {

    @NotNull JsonPacketModel getValues();

}
