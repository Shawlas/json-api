package codes.ghostface.impl.utils;

import codes.ghostface.ClientPacket;
import codes.ghostface.ServerPacket;
import codes.ghostface.impl.client.*;
import codes.ghostface.impl.server.ServerDataPacket;
import codes.ghostface.impl.server.ServerErrorPacket;
import codes.ghostface.impl.server.ServerMessagePacket;
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
        @NotNull ClientPacketHandler handler = client.getUtils().getClientHandler(client);
        @NotNull JsonObject object = new JsonObject();

        object.addProperty("type", client.getType().getDescription());

        if (handler.getCheckers().isAuthentication()) {
            @NotNull AuthenticationPacket auth = (AuthenticationPacket) handler.getPacket();
            object.addProperty("email", auth.getEmail().toString());
            object.addProperty("password", auth.getPassword());
            object.addProperty("time", auth.getTime().toString());
            return object;
        } else if (handler.getCheckers().isMessage()) {
            @NotNull MessagePacket msg = (MessagePacket) handler.getPacket();
            object.addProperty("text",  msg.getText());
            object.addProperty("time", msg.getTime().toString());
            return object;
        } else if (handler.getCheckers().isRegister()) {
            @NotNull RegisterPacket register = (RegisterPacket) handler.getPacket();
            object.addProperty("email", register.getEmail().toString());
            object.addProperty("username", register.getUsername().toString());
            object.addProperty("password", register.getPassword());
            object.addProperty("time", register.getTime().toString());
            return object;
        } else if (handler.getCheckers().isData()) {
            @NotNull DataPacket<?> data = (DataPacket<?>) handler.getPacket();
            object.add("datas", data.getData());
            return object;
        } else if (handler.getCheckers().isConnection()) {
            @NotNull ConnectionPacket connection = (ConnectionPacket) handler.getPacket();
            object.addProperty("scope", connection.getScope().name().toLowerCase());
            return object;
        } else {
            object = new JsonObject();
            object.add("value", handler.getPacket().getValues());
            return object;
        }
    }

    @Override
    public @NotNull <T extends ServerPacket> JsonElement deserialize(@NotNull T server) {
        @NotNull ServerPacketHandler handler = server.getUtils().getServerHandler(server);
        @NotNull JsonObject object = new JsonObject();
        object.addProperty("type", server.getType().getDescription());

        if (handler.getCheckers().isError()) {
            @NotNull ServerErrorPacket error = (ServerErrorPacket) handler.getPacket();
            object.addProperty("cause", error.getCause().getMessage());
            object.addProperty("time", error.getTime().toString());
            return object;
        } else if (handler.getCheckers().isResponse()) {
            @NotNull ServerResponsePacket response = (ServerResponsePacket) handler.getPacket();
            object.addProperty("content", response.getContent());
            object.addProperty("time", response.getTime().toString());
            return object;
        } else if (handler.getCheckers().isMessage()) {
            @NotNull ServerMessagePacket message = (ServerMessagePacket) handler.getPacket();
            object.addProperty("username", message.getMessage().getUsername().toString());
            object.addProperty("text", message.getMessage().getContent());
            object.addProperty("time", message.getMessage().getTime().toString());
            return object;
        } else if (handler.getCheckers().isData()) {
            @NotNull ServerDataPacket<?> data = (ServerDataPacket<?>) handler.getPacket();
            object.add("datas", data.getData());
            return object;
        } else {
            object = new JsonObject();
            object.add("values", handler.getPacket().getValues());
            return object;
        }
    }

    // Classes

    private static final class ClientPacketHandlerImpl implements ClientPacketHandler {

        private final @NotNull ClientPacket packet;
        private final @NotNull ClientCheckers checkers;

        public ClientPacketHandlerImpl(@NotNull ClientPacket packet) {
            this.packet = packet;
            this.checkers = new ClientCheckersImpl();
        }

        @Override
        public @NotNull ClientPacket getPacket() {
            return packet;
        }

        @Override
        public @NotNull ClientCheckers getCheckers() {
            return checkers;
        }

        // Classes

        private final class ClientCheckersImpl implements ClientCheckers {

            @Override
            public boolean isAuthentication() {
                return packet instanceof AuthenticationPacket;
            }

            @Override
            public boolean isRegister() {
                return packet instanceof RegisterPacket;
            }

            @Override
            public boolean isMessage() {
                return packet instanceof MessagePacket;
            }

            @Override
            public boolean isConnection() {
                return false;
            }

            @Override
            public boolean isData() {
                return packet instanceof DataPacket;
            }
        }
    }

    private static final class ServerPacketHandlerImpl implements ServerPacketHandler {

        private final @NotNull ServerPacket packet;
        private final @NotNull ServerCheckers checkers;

        public ServerPacketHandlerImpl(@NotNull ServerPacket packet) {
            this.packet = packet;
            this.checkers = new ServerCheckersImpl();
        }

        @Override
        public @NotNull ServerPacket getPacket() {
            return packet;
        }

        @Override
        public @NotNull ServerCheckers getCheckers() {
            return checkers;
        }

        // Classes

        private final class ServerCheckersImpl implements ServerCheckers {

            @Override
            public boolean isResponse() {
                return packet instanceof ServerResponsePacket;
            }

            @Override
            public boolean isError() {
                return packet instanceof ServerErrorPacket;
            }

            @Override
            public boolean isData() {
                return packet instanceof ServerDataPacket;
            }

            @Override
            public boolean isMessage() {
                return packet instanceof ServerMessagePacket;
            }
        }
    }
}
