package codes.ghostface.utils;

import codes.ghostface.ClientPacket;
import codes.ghostface.ServerPacket;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

/*
* This interface contains varius methods to access and manage packets
* */
public interface PacketUtils {

    <T extends ServerPacket> @NotNull ServerPacketHandler getServerHandler(@NotNull T server);

    <T extends ClientPacket> @NotNull ClientPacketHandler getClientHandler(@NotNull T client);

    <T extends ClientPacket> @NotNull JsonElement deserialize(@NotNull T client);

    <T extends ServerPacket> @NotNull JsonElement deserialize(@NotNull T server);

    /*
    * This interface is used to verify client packets
    * */
    interface ClientPacketHandler {
        @NotNull ClientPacket getPacket();
        @NotNull ClientCheckers getCheckers();
    }

    /*
     * This interface is used to verify server packets
     * */
    interface ServerPacketHandler {
        @NotNull ServerPacket getPacket();
        @NotNull ServerCheckers getCheckers();
    }

    // Checkers

    interface ClientCheckers {
        boolean isConnection();
        boolean isMessage();
        boolean isData();
        boolean isAuthentication();
        boolean isRegister();
        boolean isState();
        boolean isCommand();
    }

    interface ServerCheckers {
        boolean isData();
        boolean isMessage();
        boolean isResponse();
        boolean isError();
        boolean isState();
    }
}
