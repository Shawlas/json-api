package codes.ghostface.utils;

import codes.ghostface.impl.utils.PacketUtilsImpl;
import codes.ghostface.providers.ClientPacket;
import codes.ghostface.providers.ServerPacket;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

/*
* This interface contains varius methods to access and manage packets
* */
public interface PacketUtils {

    @NotNull ServerPacketContent getServerContent();

    @NotNull ClientPacketContent getClientContent();

    @NotNull ClientPacket serializeClient(@NotNull JsonElement element);

    @NotNull ServerPacket serializeServer(@NotNull JsonElement element);

    @NotNull JsonElement deserializeClient(@NotNull ClientPacket clientPacket);

    @NotNull JsonElement deserializeServer(@NotNull ServerPacket serverPacket);

    /*
    * This is used to check and parse packets from client
    * */
    interface ServerPacketContent {

        @NotNull ClientPacket getPacket();

        boolean isRequest();
        boolean isData();
        boolean isCommand();
        boolean isMessage();
        boolean isExecution();
        boolean isTransfer();
        boolean isConnection();

    }

    /*
     * THIS is used to check and parse packets from server
     * */
    interface ClientPacketContent {

        @NotNull ServerPacket getPacket();

        boolean isData();
        boolean isMessage();
        boolean isResponse();
        boolean isTransfer();
        boolean isSegment();
        boolean isConnection();
        boolean isDatagram();

    }
}
