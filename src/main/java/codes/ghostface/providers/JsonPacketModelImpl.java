package codes.ghostface.providers;

import codes.ghostface.RequestPacketImpl;
import codes.ghostface.ResponsePacketImpl;
import codes.ghostface.factory.JsonPacketModel;
import codes.ghostface.factory.Packet;
import codes.ghostface.request.ClientAuthPacket;
import codes.ghostface.request.ClientMessagePacket;
import codes.ghostface.request.ClientRegisterPacket;
import codes.ghostface.response.ServerAlertPacket;
import codes.ghostface.response.ServerErrorPacket;
import codes.ghostface.response.ServerMessagePacket;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public final class JsonPacketModelImpl implements JsonPacketModel {

    // Static initializers

    private static final @NotNull Comparator<@NotNull String> comparator;

    static {
        comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals("type")) {
                    return -1;
                } else if (o2.equals("type")) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        };
    }

    // Objects

    private final @NotNull Map<@NotNull String, @NotNull String> map = new TreeMap<>(comparator);

    public JsonPacketModelImpl() {
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        this.map.put(key, value);
    }

    @Override
    public @NotNull String get(@NotNull String key) {
        return this.map.get(key);
    }

    @Override
    public @NotNull Comparator<@NotNull String> getComparator() {
        return comparator;
    }

    @Override
    public @NotNull JsonPacketModelImpl serialize(@NotNull Packet packet) {
        @NotNull JsonPacketModelImpl json = new JsonPacketModelImpl();
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

    @Override
    public @NotNull Packet deserialize(@NotNull JsonPacketModel packet) {
        return null;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
