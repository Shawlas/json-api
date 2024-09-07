package codes.ghostface.main;

import codes.ghostface.ClientPacket;
import codes.ghostface.ServerPacket;
import codes.ghostface.exception.PacketException;
import codes.ghostface.impl.client.MessagePacket;
import codes.ghostface.impl.server.ServerDataPacket;
import codes.ghostface.impl.server.ServerResponsePacket;

import codes.ghostface.models.Message;
import codes.ghostface.models.Username;
import codes.laivy.serializable.json.JsonSerializable;
import org.jetbrains.annotations.NotNull;

import java.io.InvalidClassException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;

public final class Main {
    public static void main(String[] args) throws InvalidClassException, PacketException {
        @NotNull ClientPacket packet = new MessagePacket("hello world");
        System.out.println(packet.getUtils().deserialize(packet));

        @NotNull ServerPacket server = new ServerResponsePacket("success", OffsetDateTime.now());
        System.out.println(server.getUtils().deserialize(server));

        @NotNull ServerDataPacket<Message> server1 = new ServerDataPacket<>(new Message(new Username("shawlas"), "hello", Instant.now()));
        System.out.println(server1.getData());
        @NotNull Message message = Objects.requireNonNull(new JsonSerializable().deserialize(Message.class, server1.getData()));
        System.out.println(message);

    }
}
