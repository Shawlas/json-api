package codes.ghostface;

import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface Packet extends Serializable {

    @NotNull JsonElement getValues();

    @NotNull PacketUtils getUtils();

}
