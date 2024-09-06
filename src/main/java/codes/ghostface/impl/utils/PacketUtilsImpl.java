package codes.ghostface.impl.utils;

import codes.ghostface.ClientPacket;
import codes.ghostface.ServerPacket;
import codes.ghostface.impl.client.AuthenticationPacket;
import codes.ghostface.impl.client.MessagePacket;
import codes.ghostface.impl.client.RegisterPacket;
import codes.ghostface.impl.server.ServerErrorPacket;
import codes.ghostface.impl.server.ServerResponsePacket;
import codes.ghostface.utils.PacketUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public final class PacketUtilsImpl implements PacketUtils {

    // Implementations

    @Override
    public @NotNull <T extends ServerPacket> ServerPacketHandler getServerHandler(@NotNull T server) {
        return new ServerPacketHandlerImpl(server);
    }

    @Override
    public @NotNull <T extends ClientPacket> ClientPacketHandler getClientHandler(@NotNull T client) {
        return new ClientPacketHandlerImpl(client);
    }

    @Override
    public @NotNull <T extends ClientPacket> JsonElement deserialize(@NotNull T client) {
        final @NotNull ClientCheckers handler = client.getUtils().getClientHandler(client).getCheckers();
        @NotNull JsonObject object = new JsonObject();

        object.addProperty("type", client.getType().getDescription());

        if (handler.isAuthentication()) {
            @NotNull AuthenticationPacket auth = (AuthenticationPacket) client;
            object.addProperty("email", auth.getEmail().toString());
            object.addProperty("password", auth.getPassword());
            object.addProperty("time", auth.getTime().toString());
            return object;
        } else if (handler.isMessage()) {
            @NotNull MessagePacket msg = (MessagePacket) client;
            object.addProperty("text",  msg.getText());
            object.addProperty("time", msg.getTime().toString());
            return object;
        } else if (handler.isRegister()) {
            @NotNull RegisterPacket register = (RegisterPacket) client;
            object.addProperty("email", register.getEmail().toString());
            object.addProperty("username", register.getUsername().toString());
            object.addProperty("password", register.getPassword());
            object.addProperty("time", register.getTime().toString());
            return object;
        } else {
            object = new JsonObject();
            object.add("value", client.getValues());
            return object;
        }
    }

    @Override
    public @NotNull <T extends ServerPacket> JsonElement deserialize(@NotNull T server) {
        final @NotNull ServerCheckers checkers = server.getUtils().getServerHandler(server).getCheckers();
        @NotNull JsonObject object = new JsonObject();
        object.addProperty("type", server.getType().getDescription());

        if (checkers.isError()) {
            @NotNull ServerErrorPacket error = (ServerErrorPacket) server;
            object.addProperty("cause", error.getCause());
            object.addProperty("time", error.getTime().toString());
            return object;
        } else if (checkers.isResponse()) {
            @NotNull ServerResponsePacket response = (ServerResponsePacket) server;
            object.addProperty("content", response.getContent());
            object.addProperty("time", response.getTime().toString());
            return object;
        } else {
            object = new JsonObject();
            object.add("values", server.getValues());
            return object;
        }
    }

}
