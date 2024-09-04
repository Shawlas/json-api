package codes.ghostface.utils;

import codes.ghostface.protocol.RequestPacketImpl;
import codes.ghostface.protocol.ResponsePacketImpl;
import codes.ghostface.factory.Packet;
import codes.ghostface.factory.PacketUtils;
import codes.ghostface.impl.request.ClientAuthPacket;
import codes.ghostface.impl.request.ClientMessagePacket;
import codes.ghostface.impl.request.ClientRegisterPacket;
import codes.ghostface.impl.response.ServerAlertPacket;
import codes.ghostface.impl.response.ServerErrorPacket;
import codes.ghostface.impl.response.ServerMessagePacket;
import codes.ghostface.providers.ClientPacket;
import codes.ghostface.model.JsonPacketImpl;
import codes.ghostface.providers.ServerPacket;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;

public class PacketUtilsImpl implements PacketUtils {

    @Override
    public @NotNull JsonPacketImpl parseJson(@NotNull Packet packet) {
        if (Modifier.isAbstract(packet.getClass().getModifiers())) {
            throw new IllegalArgumentException("Cannot deserialize abstract class");
        }

        @NotNull JsonPacketImpl json = new JsonPacketImpl();
        @NotNull RequestPacketImpl request;
        @NotNull ResponsePacketImpl response;

        // server side
        if (packet instanceof ClientPacket) {
            response = new ResponsePacketImpl((ClientPacket) packet);

            if (response.isAuthentication()) {
                @NotNull ClientAuthPacket authPacket = (ClientAuthPacket) response.getPacket();
                json.add("type", "authentication");
                json.add("email", authPacket.getEmail().toString());
                json.add("password", authPacket.getPassword().toString());
                return json;
            } else if (response.isRegister()) {
                @NotNull ClientRegisterPacket registerPacket = (ClientRegisterPacket) response.getPacket();
                json.add("type", "registration");
                json.add("email", registerPacket.getEmail().toString());
                json.add("password", registerPacket.getPassword().toString());
                json.add("username", registerPacket.getUsername().toString());
                return json;
            } else if (response.isMessage()) {
                @NotNull ClientMessagePacket messagePacket = (ClientMessagePacket) response.getPacket();
                json.add("type", "message");
                json.add("text", messagePacket.getText());
                json.add("time", messagePacket.getTime().toString());
                return json;
            }
            // client side
        } else if (packet instanceof ServerPacket) {
            request = new RequestPacketImpl((ServerPacket) packet);

            if (request.isError()) {
                @NotNull ServerErrorPacket errorPacket = (ServerErrorPacket) request.getPacket();
                json.add("type", "error");
                json.add("cause", errorPacket.getCause().name().toLowerCase());
                return json;
            } else if (request.isAlert()) {
                @NotNull ServerAlertPacket alertPacket = (ServerAlertPacket) request.getPacket();
                json.add("type", "alert");
                json.add("cause", alertPacket.getAlert().name().toLowerCase());
                return json;
            } else if (request.isServerMessage()) {
                @NotNull ServerMessagePacket messagePacket = (ServerMessagePacket) request.getPacket();
                json.add("type", "message");
                json.add("username", messagePacket.getUsername().toString());
                json.add("text", messagePacket.getText());
                json.add("time", messagePacket.getTime().toString());
                return json;
            }
        }

        throw new IllegalArgumentException("cannot parse '" + packet + "' cause your type is invalid");
    }
}
