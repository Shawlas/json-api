package codes.ghostface;

import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

public interface Packet {

    @NotNull JsonElement getValues();

    @NotNull PacketUtils getUtils();

}
